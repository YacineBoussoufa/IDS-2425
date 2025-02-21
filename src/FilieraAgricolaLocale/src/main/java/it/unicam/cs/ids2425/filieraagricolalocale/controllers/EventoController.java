package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ManifestazioneRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VisitaRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.EventoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEventoDati;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/eventi")

public class EventoController {

    private EventoService es;

    EventoController(VisitaRepository v, ManifestazioneRepository m) {

        es = new EventoService(v, m);
        MiddlewareEvento me = MiddlewareEvento.link(new MiddlewareEventoDati(es));
        es.setMiddleware(me);

        //Todo: TEST

        // es.aggiungiEventoV(
        //         new VisitaBuilder().setNome("Test").setDescrizione("Test")
        //                 .setNumeroMaxPartecipanti(10).setPuntoDiInteresse(new POI(0,0,0,TipoPOI.Evento))
        //                 .setProposta(new Proposta(null, null,null))
        //                 .setData(Date.from(Instant.now()))
        //                 .setAnimatore(new Utente("Animatore", "Animatore", Date.from(Instant.now()),
        //                         "animatoreTest", "password", null))
        //                 .setPersonePartecipanti(Set.of(new Utente("test","test", Date.from(Instant.now()), "test",
        //                                 "test", null)))
        //                 .build()
        // );

        // es.aggiungiEventoM(
        //         new ManifestazioneBuilder().setNome("Test").setDescrizione("Test")
        //                 .setNumeroMaxPartecipanti(10).setPuntoDiInteresse(new POI(0,0,0,TipoPOI.Evento))
        //                 .setData(Date.from(Instant.now()))
        //                 .setAnimatore(new Utente("Animatore", "Animatore", Date.from(Instant.now()),
        //                         "animatoreTest", "password", null))
        //                 .setPersonePartecipanti(Set.of(new Utente("test","test", Date.from(Instant.now()), "test",
        //                         "test", null)))
        //                 .setAziendePartecipanti(Set.of(new Venditore("Test Azienda", "PIVA", "usernameAzienda", "passwordAzienda"
        //                 , null, "Azienda di Test", new POI(0,0,0,TipoPOI.Azienda)
        //                 )))
        //                 .build()
        // );

    }

    @RequestMapping(value = "/visita/crea", method = RequestMethod.POST)
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

    @RequestMapping(value = "/manifestazione/crea", method = RequestMethod.POST)
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
            es.modificaEvento(id, evento);
            return new ResponseEntity<>("Evento modificato con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/modifica/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editManifestazione(@PathVariable("id") int id, @RequestBody Manifestazione evento) {
        try {
            es.modificaEvento(id, evento);
            return new ResponseEntity<>("Evento modificato con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/eliminaVisita/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteVisita(@PathVariable("id") int id) {
        try {
            es.rimuoviVisita(id);
            return new ResponseEntity<>("Visita eliminata con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminaManifestazione/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteManifestazione(@PathVariable("id") int id) {

        try {
            es.rimuoviManifestazione(id);
            return new ResponseEntity<>("Manifestazione eliminata con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/accettaProposta/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> acceptProposta(@PathVariable("id") int id) {

        try {
            es.accettaProposta(id);
            return new ResponseEntity<>("Proposta della Visita accettata con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAVisita(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/utentiPartecipanti/manifestazione/{id}")
    public ResponseEntity<Object> getUtentiPartecipantiAManifestazione(@PathVariable("id") int id) {
        return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAManifestazione(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/aziendePartecipanti/manifestazione/{id}")
    public ResponseEntity<Object> getAziendePartecipantiAManifestazione(@PathVariable("id") int id) {
        return new ResponseEntity<>(es.visualizzaAziendePartecipantiAManifestazione(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/aggiungiUtentePartecipante/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentePartecipanteAVisita(@PathVariable("id") int id, @RequestBody Utente user) {
        try {
            es.aggiungiUtentePartecipanteAVisita(id, user);
            return new ResponseEntity<>("Utente aggiunto con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentePartecipante/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentePartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody Utente user) {
        try {
            es.aggiungiUtentePartecipanteAManifestazione(id, user);
            return new ResponseEntity<>("Utente aggiunto con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiAziendaPartecipante/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addAziendaPartecipanteAManifestazione(@PathVariable("id") int id, @RequestBody Venditore user) {
        try {
            es.aggiungiAziendaPartecipanteAManifestazione(id, user);
            return new ResponseEntity<>("Azienda aggiunta con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentiPartecipanti/visita/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentiPartecipantiAVisita(@PathVariable("id") int id, @RequestBody Set<Utente> user) {
        try {
            es.aggiungiUtentiPartecipantiAVisita(id, user);
            return new ResponseEntity<>("Utenti aggiunti con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiUtentiPartecipanti/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addUtentiPartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<Utente> user) {
        try {
            es.aggiungiUtentiPartecipantiAManifestazione(id, user);
            return new ResponseEntity<>("Utenti aggiunti con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/aggiungiAziendePartecipanti/manifestazione/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> addAziendePartecipantiAManifestazione(@PathVariable("id") int id, @RequestBody Set<Venditore> user) {
        try {
            es.aggiungiAziendePartecipantiAManifestazione(id, user);
            return new ResponseEntity<>("Aziende aggiunte con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
