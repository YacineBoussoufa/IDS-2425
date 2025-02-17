package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.EventoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEventoDati;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/evento")

public class EventoController {

    private EventoService es;

    EventoController() {

        MiddlewareEvento me = MiddlewareEvento.link(new MiddlewareEventoDati(es));
        es = new EventoService(me);
    }

    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public ResponseEntity<Object> createEvento(@RequestBody EventoAbstract evento) {

        try {
            es.aggiungiEvento(evento);
            return new ResponseEntity<>("Evento creato con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/modifica/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editEvento(@PathVariable("id") int id, @RequestBody EventoAbstract evento) {
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
    public ResponseEntity<Object> deleteVisita(@RequestBody int id) {
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
    public ResponseEntity<Object> deleteManifestazione(@RequestBody int id) {

        try {
            es.rimuoviManifestazione(id);
            return new ResponseEntity<>("Manifestazione eliminata con successo.", HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/accettaProposta/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> acceptProposta(@RequestBody int id) {

        try {
            es.accettaProposta(id);
            return new ResponseEntity<>("Proposta della Visita accettata con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/listaVisiteAccettate")
    public ResponseEntity<Object> getListaVisiteAccettate() {
        return new ResponseEntity<>(es.getRepoVisiteAccettate(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listaVisiteNonAccettate")
    public ResponseEntity<Object> getListaVisiteNonAccettate() {
        return new ResponseEntity<>(es.getRepoVisiteNonAccettate(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listaVisite")
    public ResponseEntity<Object> getListaVisite() {
        return new ResponseEntity<>(es.getRepoVisite(), HttpStatus.OK);
    }

    @RequestMapping(value = "/listaManifestazioni")
    public ResponseEntity<Object> getListaManifestazioni() {
        return new ResponseEntity<>(es.getRepoManifestazioni(), HttpStatus.OK);
    }

    @RequestMapping(value = "/visualizzaUtentiPartecipantiAVisita/{id}")
    public ResponseEntity<Object> getUtentiPartecipantiAVisita(@RequestBody int id) {
        return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAVisita(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/visualizzaUtentiPartecipantiAManifestazione/{id}")
    public ResponseEntity<Object> getUtentiPartecipantiAManifestazione(@RequestBody int id) {
        return new ResponseEntity<>(es.visualizzaUtentiPartecipantiAManifestazione(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/visualizzaAziendePartecipantiAManifestazione/{id}")
    public ResponseEntity<Object> getAziendePartecipantiAManifestazione(@RequestBody int id) {
        return new ResponseEntity<>(es.visualizzaAziendePartecipantiAManifestazione(id), HttpStatus.OK);
    }

    //TODO Aggiungere i vari partecipanti

    @RequestMapping(value = "/aggiungiUtentePartecipanteAVisita/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/aggiungiUtentePartecipanteAManifestazione/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/aggiungiAziendaPartecipanteAManifestazione/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/aggiungiUtentiPartecipantiAVisita/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/aggiungiUtentiPartecipantiAManifestazione/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/aggiungiAziendePartecipantiAManifestazione/{id}", method = RequestMethod.PUT)
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
