package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NumeroMassimoUtentiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.EventoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> createVisita(@RequestBody Visita evento) {

        try {
            es.aggiungiEventoV(evento);
            return new ResponseEntity<>("Visita creata con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/crea/manifestazione", method = RequestMethod.POST)
    public ResponseEntity<Object> createManifestazione(@RequestBody Manifestazione evento) {

        try {
            es.aggiungiEventoM(evento);
            return new ResponseEntity<>("Manifestazione creata con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/modifica/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editVisita(@PathVariable("id") int id, @RequestBody Visita evento) {
        try {
            Account account = us.getCurrentUser();
            auth.controlloAutorizzazioneEvento(evento, account);
            evento.setId(id);
            es.modificaEvento(id, evento);
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
    public ResponseEntity<Object> editManifestazione(@PathVariable("id") int id, @RequestBody Manifestazione evento) {
        try {
            Account account = us.getCurrentUser();
            auth.controlloAutorizzazioneEvento(evento, account);
            evento.setId(id);
            es.modificaEvento(id, evento);
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
    public ResponseEntity<Object> addUtentePartecipanteAVisita(@PathVariable("id") int id, @RequestBody Utente user) {
        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneEvento(visita, account);

            es.aggiungiUtentePartecipanteAVisita(id, user);
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
    public ResponseEntity<Object> addUtentePartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody Utente user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiUtentePartecipanteAManifestazione(id, user);
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
    public ResponseEntity<Object> addAziendaPartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody Venditore user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiAziendaPartecipanteAManifestazione(id, user);
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
    public ResponseEntity<Object> addUtentiPartecipantiAVisita(@PathVariable("id") int id, @RequestBody Set<Utente> user) {
        try {
            Account account = us.getCurrentUser();
            Visita visita = es.getVisita(id);
            auth.controlloAutorizzazioneEvento(visita, account);

            es.aggiungiUtentiPartecipantiAVisita(id, user);
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
    public ResponseEntity<Object> addUtentiPartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<Utente> user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiUtentiPartecipantiAManifestazione(id, user);
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
    public ResponseEntity<Object> addAziendePartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<Venditore> user) {
        try {
            Account account = us.getCurrentUser();
            Manifestazione manifestazione = es.getManifestazione(id);
            auth.controlloAutorizzazioneEvento(manifestazione, account);

            es.aggiungiAziendePartecipantiAManifestazione(id, user);
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
