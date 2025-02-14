package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Bozza;
import it.unicam.cs.ids2425.filieraagricolalocale.model.InConvalida;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.StatoApprovazione;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApprovazioneService {

    //todo mock database
    public static Map<Integer, Prodotto> repoProdotti = new HashMap<>();

    /**
     * Invia la richiesta per convalidare un prodotto, settando lo stato in InConvalida
     *
     * @param id Identificatore del prodotto che si vuole far convalidare
     */
    public void inviaRichiesta(int id) {
        Prodotto prodotto = repoProdotti.get(id);

        if (prodotto.getStato() instanceof Bozza) {
            prodotto.getStato().pubblica();
        }
    }

    /**
     * Metodo per ottenere tutti i prodotti in convalida
     *
     * @return Lista dei prodotti con lo stato InConvalida
     */
    public List<Prodotto> getProdottiInConvalida() {
        List<Prodotto> prodotti = new ArrayList<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            if (prodotto.getStato() instanceof InConvalida) {
                prodotti.add(prodotto);
            }
        }

        return prodotti;
    }

    /**
     * Cambia lo stato del prodotto in convalida in base al valore di esito.
     *
     * @param id Identificatore del prodotto in convalida
     * @param esito Esito della convalida
     *
     * @throws DatiIncorrettiException se il prodotto non è pronto alla convalida
     */
    public void approva(int id, boolean esito) {
        Prodotto prodotto = repoProdotti.get(id);
        StatoApprovazione stato = prodotto.getStato();

        if (!(stato instanceof InConvalida)) throw new DatiIncorrettiException();

        if (esito) {
            ((InConvalida) stato).approva();
        }

        stato.pubblica();
    }

}
