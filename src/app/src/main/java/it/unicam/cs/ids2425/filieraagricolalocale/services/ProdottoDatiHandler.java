package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.TipoPOI;

public class ProdottoDatiHandler implements Handler {

    Prodotto prodotto;

    public ProdottoDatiHandler(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    @Override
    public boolean handle() {
        if (prodotto.getNome().isEmpty()) return false;

        else if (prodotto.getPrezzo() <= 0) return false;

        else if (prodotto.getQuantita() <= 0) return false;

        else if (!prodotto.getPoi().getTipoPOI().equals(TipoPOI.Prodotto)) return false;

        else if (prodotto.getVenditore() == null) return false;

        else if (prodotto.getPoi() == null) return false;

        else if (prodotto.getData() == null) return false;

        else return true;
    }
}
