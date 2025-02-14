package it.unicam.cs.ids2425.filieraagricolalocale.services.middlewareEvento;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

public class MiddlewareEvento {
    private final Map<Integer, Evento> eventi; // Reference to stored events

    public EventoMiddleware(Map<Integer, Evento> eventi) {
        this.eventi = eventi;
    }

    public boolean check(Evento evento) {
        if (evento.getNome() == null || evento.getNome().isEmpty()) return false;
        if (evento.getDescrizione() == null || evento.getDescrizione().isEmpty()) return false;
        if (evento.getData() == null) return false;
        if (evento.getPuntoDiInteresse() == null) return false;
        if (evento.getNumeroMaxPartecipanti() <= 0) return false;
        if (evento.getPuntoDiInteresse() == null) return false;

        for (Evento eventiEsistenti : eventi.values()) {
            if (eventiEsistenti.getData().equals(evento.getData()) &&
                    eventiEsistenti.getPuntoDiInteresse().equals(evento.getPuntoDiInteresse())) {
                return false;
            }
        }

    }

}
