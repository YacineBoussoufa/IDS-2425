package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.EventoMapper;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.ManifestazioneDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.VisitaDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NumeroMassimoUtentiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.EventoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/eventi")

public class EventoController {

    private final EventoService es;
    private final UserService us;
    private final AutorizzazioneService auth;

    EventoController(InitFacade i) {
        es = i.geteS();
        us = i.getuS();
        auth = i.getAuthS();
    }

    @RequestMapping(value = "/crea/visita", method = RequestMethod.POST)
    public ResponseEntity<Object> createVisita(@RequestBody VisitaDTO evento) {

        try {

            Visita eventoV = EventoMapper.ToEntity(evento, us);

            es.aggiungiEventoV(eventoV);
            return new ResponseEntity<>("Visita creata con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/crea/manifestazione", method = RequestMethod.POST)
    public ResponseEntity<Object> createManifestazione(@RequestBody ManifestazioneDTO evento) {

        try {

            Manifestazione eventoV = EventoMapper.ToEntity(evento, us);

            es.aggiungiEventoM(eventoV);
            return new ResponseEntity<>("Manifestazione creata con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/modifica/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editVisita(@PathVariable("id") int id, @RequestBody VisitaDTO evento) {
        try {

            Visita eventoV = EventoMapper.ToEntity(evento, us);

            Account account = us.getCurrentUser();
            auth.controlloAutorizzazioneEvento(eventoV, account);
            eventoV.setId(id);
            es.modificaEvento(id, eventoV);
            return new ResponseEntity<>("Visita modificata con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/modifica/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editManifestazione(@PathVariable("id") int id, @RequestBody ManifestazioneDTO evento) {
        try {

            Manifestazione eventoV = EventoMapper.ToEntity(evento, us);
        
            Account account = us.getCurrentUser();
            auth.controlloAutorizzazioneEvento(eventoV, account);
            eventoV.setId(id);
            es.modificaEvento(id, eventoV);
            return new ResponseEntity<>("Manifestazione modificata con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/elimina/visita/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteVisita(@PathVariable("id") int id) {
        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneEvento(visita, account);

            es.rimuoviVisita(id);
            return new ResponseEntity<>("Visita eliminata con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/elimina/manifestazione/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteManifestazione(@PathVariable("id") int id) {

        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.rimuoviManifestazione(id);
            return new ResponseEntity<>("Manifestazione eliminata con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/accettaProposta/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> acceptProposta(@PathVariable("id") int id) {

        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneProposta(visita, account);

            es.accettaProposta(id);
            return new ResponseEntity<>("Proposta della Visita accettata con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista delle visite di un animatore in GET con il suo username
     */
    @RequestMapping(value = "/visite/caricate", method = RequestMethod.GET)
    public ResponseEntity<Object> getVisiteAnimatore(@PathVariable String username) {

        try {
            Collection<Visita> visite = es.visualizzaVisiteAnimatore(username);
            return new ResponseEntity<>(visite, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista delle manifestazioni di un animatore in GET con il suo username
     */
    @RequestMapping(value = "/manifestazioni/caricate", method = RequestMethod.GET)
    public ResponseEntity<Object> getManifestazioniAnimatore(@PathVariable String username) {

        try {
            Collection<Manifestazione> visite = es.visualizzaManifestazioniAnimatore(username);
            return new ResponseEntity<>(visite, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/visite/accettate")
    public ResponseEntity<Object> getListaVisiteAccettate() {
        return new ResponseEntity<>(es.getRepoVisiteAccettate(), HttpStatus.OK);
    }

    @RequestMapping(value = "/visite/nonAccettate")
    public ResponseEntity<Object> getListaVisiteNonAccettate() {
        return new ResponseEntity<>(es.getRepoVisiteNonAccettate(), HttpStatus.OK);
    }

    @RequestMapping(value = "/visite")
    public ResponseEntity<Object> getListaVisite() {
        return new ResponseEntity<>(es.getRepoVisite(), HttpStatus.OK);
    }

    @RequestMapping(value = "/manifestazioni")
    public ResponseEntity<Object> getListaManifestazioni() {
        return new ResponseEntity<>(es.getRepoManifestazioni(), HttpStatus.OK);
    }

    @RequestMapping(value = "/utentiPartecipanti/visita/{id}")
    public ResponseEntity<Object> getUtentiPartecipantiAVisita(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAVisita(id), HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/utentiPartecipanti/manifestazione/{id}")
    public ResponseEntity<Object> getUtentiPartecipantiAManifestazione(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAManifestazione(id), HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/aziendePartecipanti/manifestazione/{id}")
    public ResponseEntity<Object> getAziendePartecipantiAManifestazione(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(es.visualizzaAziendePartecipantiAManifestazione(id), HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/aggiungiUtentePartecipante/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentePartecipanteAVisita(@PathVariable("id") int id, @RequestBody String user) {
        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneEvento(visita, account);

            es.aggiungiUtentePartecipanteAVisita(id, us.getUtente(user));
            return new ResponseEntity<>("Utente aggiunto con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentePartecipante/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentePartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody String user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiUtentePartecipanteAManifestazione(id, us.getUtente(user));
            return new ResponseEntity<>("Utente aggiunto con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiAziendaPartecipante/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addAziendaPartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody String user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiAziendaPartecipanteAManifestazione(id, us.getVenditore(user));
            return new ResponseEntity<>("Azienda aggiunta con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentiPartecipanti/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentiPartecipantiAVisita(@PathVariable("id") int id, @RequestBody Set<String> users) {
        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneEvento(visita, account);

            Set<Utente> personeP = new HashSet<>();
            for (String user : users) {
                  personeP.add(us.getUtente(user));
            }
            es.aggiungiUtentiPartecipantiAVisita(id, personeP);
            return new ResponseEntity<>("Utenti aggiunti con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentiPartecipanti/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentiPartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<String> users) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            Set<Utente> personeP = new HashSet<>();
            for (String user : users) {
                  personeP.add(us.getUtente(user));
            }
            es.aggiungiUtentiPartecipantiAManifestazione(id, personeP);
            return new ResponseEntity<>("Utenti aggiunti con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiAziendePartecipanti/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addAziendePartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<String> users) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            Set<Venditore> venditoreP = new HashSet<>();
            for (String user : users) {
                  venditoreP.add(us.getVenditore(user));
            }

            es.aggiungiAziendePartecipantiAManifestazione(id, venditoreP);
            return new ResponseEntity<>("Aziende aggiunte con successo.", HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(NumeroMassimoUtentiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
