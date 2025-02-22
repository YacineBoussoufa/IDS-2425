package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento;

import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.EventoService;

import java.util.Map;

public class MiddlewareEventoDati extends MiddlewareEvento {

    private EventoService eventoService;


    public MiddlewareEventoDati(EventoService eventi) {
        this.eventoService = eventi;
    }

    public boolean check(EventoAbstract evento) {
        if (evento.getNome() == null || evento.getNome().isEmpty()) return false;
        if (evento.getDescrizione() == null || evento.getDescrizione().isEmpty()) return false;
        if (evento.getData() == null) return false;
        if (evento.getPuntoDiInteresse() == null) return false;
        if (evento.getNumeroMaxPartecipanti() <= 0) return false;

        if(evento instanceof Visita) {
            for (Visita eventiEsistenti : eventoService.getRepoVisite()) {
                if (eventiEsistenti.getData().equals(evento.getData()) &&
                        eventiEsistenti.getPuntoDiInteresse().equals(evento.getPuntoDiInteresse())) {
                    return false;
                }
            }
        }else if(evento instanceof Manifestazione) {

            for (Manifestazione eventiEsistenti : eventoService.getRepoManifestazioni()) {
                if (eventiEsistenti.getData().equals(evento.getData()) &&
                        eventiEsistenti.getPuntoDiInteresse().equals(evento.getPuntoDiInteresse())) {
                    return false;
                }
            }
        }
        else { return false; }

        return checkNext(evento);
    }

}