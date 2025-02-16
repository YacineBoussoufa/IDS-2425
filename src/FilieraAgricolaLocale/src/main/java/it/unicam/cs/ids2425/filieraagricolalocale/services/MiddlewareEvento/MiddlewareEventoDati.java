package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento;

import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.util.Map;

public class MiddlewareEventoDati extends MiddlewareEvento {
    private final Map<Integer, EventoAbstract> eventi; // Reference to stored events

    public MiddlewareEventoDati(Map<Integer, EventoAbstract> eventi) {
        this.eventi = eventi;
    }

    public boolean check(EventoAbstract evento) {
        if (evento.getNome() == null || evento.getNome().isEmpty()) return false;
        if (evento.getDescrizione() == null || evento.getDescrizione().isEmpty()) return false;
        if (evento.getData() == null) return false;
        if (evento.getPuntoDiInteresse() == null) return false;
        if (evento.getNumeroMaxPartecipanti() <= 0) return false;

        for (EventoAbstract eventiEsistenti : eventi.values()) {
            if (eventiEsistenti.getData().equals(evento.getData()) &&
                    eventiEsistenti.getPuntoDiInteresse().equals(evento.getPuntoDiInteresse())) {
                return false;
            }
        }

        return checkNext(evento);
    }

}