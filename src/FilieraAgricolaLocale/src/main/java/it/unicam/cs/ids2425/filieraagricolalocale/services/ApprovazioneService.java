package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.InConvalida;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.StatoApprovazione;

public class ApprovazioneService {

    //todo completare quando curatore sarà implementato
    public void inviaRichiesta(Prodotto prodotto) {

    }

    /**
     * Cambia lo stato del prodotto in convalida in base al valore di esito.
     *
     * @param prodotto Prodotto in convalida
     * @param esito Esito della convalida
     */
    public void approva(Prodotto prodotto, boolean esito) {
        StatoApprovazione stato = prodotto.getStato();

        if (!(stato instanceof InConvalida)) throw new IllegalArgumentException();

        if (esito) {
            ((InConvalida) stato).approva();
        }

        stato.pubblica();
    }

    //TODO
    public List<Contenuto> visualizzaContenutiDaApprovare(){
        return null;
    }

    //TODO maybe pubblica should return a boolean?
    public boolean pubblicaContenuto(Contenuto p){
        p.getStato().pubblica();
        return false;
    }
}
