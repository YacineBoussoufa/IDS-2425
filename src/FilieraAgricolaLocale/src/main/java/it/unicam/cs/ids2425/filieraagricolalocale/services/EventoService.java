package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;

import java.util.*;

public class EventoService {

    private Map<Integer, EventoAbstract> eventi = new HashMap<>();
    private int idCounter = 0;
    private MiddlewareEvento middleware;

    //TODO CONTROLLI

    public void aggiungiEvento(EventoAbstract evento) {

        if (middleware.check(evento)) {
            eventi.put(idCounter++, evento);
        } else {
            throw new DatiIncorrettiException();
        }

    }

    public void modificaEvento(int id, EventoAbstract eventoModificato) {
        if (!eventi.containsKey(id)) {
            throw new DatiIncorrettiException();
        }

        if (middleware.check(eventoModificato)) {
            eventi.put(id, eventoModificato);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void rimuoviEvento(int id) {
        if (!eventi.containsKey(id)) {
            throw new DatiIncorrettiException();
        }
        eventi.remove(id);
    }

    public void aggiungiPartecipante(int id, Utente utente) {

    }

    public void aggiungiPartecipanti(int id, Set<Utente> nuoviPartecipanti) {
        EventoAbstract evento = eventi.get(id);
        if (evento instanceof Manifestazione) {
            ((Manifestazione) evento).getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else if (evento instanceof Visita) {
            ((Visita) evento).getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public Set<Utente> visualizzaPartecipanti(int id) {
        EventoAbstract evento = eventi.get(id);
        if (evento instanceof Manifestazione) {
            return ((Manifestazione) evento).getPersonePartecipanti();
        } else if (evento instanceof Visita) {
            return ((Visita) evento).getPersonePartecipanti();
        }
        throw new DatiIncorrettiException();
    }

    public Map<Integer, EventoAbstract> getEventi() {
        return eventi;
    }

}