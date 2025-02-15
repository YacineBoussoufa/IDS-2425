package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.middlewareEvento;

import java.text.DateFormat;
import java.util.*;

public class EventoService {

    private Map<Integer, Evento> eventi = new HashMap<>();
    private int idCounter = 0;
    private MiddlewareEvento middleware;

    //TODO CONTROLLI

    public void aggiungiEvento(Evento evento) {

        if (middleware.check(evento)) {
            eventi.put(idCounter++, evento);
        } else {
            throw new DatiIncorrettiException();
        }

    }

    public void modificaEvento(int id, Evento eventoModificato) {
        if (!eventi.containsKey(id)) {
            throw new DatiIncorrettiException();
        }

        if (middleware.check(evento)) {
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

    public void aggiungiPartecipanti(int id, Set<Utente> nuoviPartecipanti) {
        Evento evento = eventi.get(id);
        if (evento instanceof Manifestazione) {
            ((Manifestazione) evento).getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else if (evento instanceof Visita) {
            ((Visita) evento).getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public Set<Utente> visualizzaPartecipanti(int id) {
        Evento evento = eventi.get(id);
        if (evento instanceof Manifestazione) {
            return ((Manifestazione) evento).getPersonePartecipanti();
        } else if (evento instanceof Visita) {
            return ((Visita) evento).getPersonePartecipanti();
        }
        throw new DatiIncorrettiException();
    }

    public Map<Integer, Evento> getEventi() {
        return eventi;
    }

}