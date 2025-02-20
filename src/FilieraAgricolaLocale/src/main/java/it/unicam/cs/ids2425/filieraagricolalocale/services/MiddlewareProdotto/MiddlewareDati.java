package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

public class MiddlewareDati extends MiddlewareProdotto {

    @Override
    public boolean check(Contenuto contenuto) {
        if (contenuto.getNome() == null || contenuto.getNome().isEmpty()) return false;

        else if (contenuto.getPrezzo() <= 0) return false;

        else if (contenuto.getQuantita() <= 0) return false;

        else if (contenuto.getVenditore() == null) return false;

        else if (contenuto.getData() == null) return false;

        return checkNext(contenuto);
    }

}
