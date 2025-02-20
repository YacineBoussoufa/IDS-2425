package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;

public class MiddlewarePOI extends MiddlewareProdotto {


    @Override
    public boolean check(Contenuto contenuto) {
        if (contenuto instanceof Prodotto prodotto) {

            return prodotto.getPoi() != null;

        } else if (contenuto instanceof Pacchetto pacchetto) {

            for (Prodotto prodotto : pacchetto.getListaProdotti()) {
                if (prodotto.getPoi() == null) {
                    return false;
                }
            }

        } else {
            return true;
        }

        return true;
    }
}
