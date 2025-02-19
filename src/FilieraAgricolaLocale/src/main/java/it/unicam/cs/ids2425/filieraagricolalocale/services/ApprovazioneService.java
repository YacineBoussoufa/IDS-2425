package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("ApprovazioneService")
public class ApprovazioneService {

    //todo mock database
    public static Map<Integer, Prodotto> repoProdotti = ProdottoService.repoProdotti;
    public static Map<Integer, Pacchetto> repoPacchetti = ProdottoService.repoPacchetti;

    /**
     * Invia la richiesta per convalidare un prodotto, settando lo stato in InConvalida
     *
     * @param id Identificatore del prodotto
     *
     * @throws ProdottoNonTrovatoException se l'id non corrisponde a nessun prodotto nel database
     */
    public void inviaRichiestaProdotto(int id) {
        if (!repoProdotti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste prodotto con id " + id);
        Contenuto prodotto = repoProdotti.get(id);

        if (prodotto.getStato() instanceof Bozza) {
            prodotto.getStato().pubblica();
        }
    }

    /**
     * Invia la richiesta per convalidare un pacchetto, settando il suo stato e
     * lo stato di tutti i suoi prodotti in InConvalida se sono bozze
     *
     * @param id Identificatore del pacchetto
     *
     * @throws ProdottoNonTrovatoException se l'id non corrisponde a nessun pacchetto nel database
     */
    public void inviaRichiestaPacchetto(int id) {
        if (!repoPacchetti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste pacchetto con id " + id);
        Pacchetto pacchetto = repoPacchetti.get(id);

        if (pacchetto.getStato() instanceof Bozza) {

            for (Prodotto prodotto : pacchetto.getListaProdotti()) {
                if (prodotto.getStato() instanceof Bozza) {
                    prodotto.getStato().pubblica();
                }
            }

            pacchetto.getStato().pubblica();
        }

    }

    /**
     * Cambia lo stato del prodotto in convalida in base al valore di esito.
     *
     * @param id Identificatore del prodotto in convalida
     * @param esito Esito della convalida
     *
     * @throws ProdottoNonTrovatoException se l'id non corrisponde a nessun prodotto
     * @throws DatiIncorrettiException se il prodotto non è pronto alla convalida
     */
    public void approvaProdotto(int id, boolean esito) {
        if (!repoProdotti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste prodotto con id " + id);
        Prodotto prodotto = repoProdotti.get(id);
        StatoApprovazione stato = prodotto.getStato();

        if (!(stato instanceof InConvalida)) throw new DatiIncorrettiException(
                "Non si può approvare un prodotto non in stato di convalida");

        if (esito) {
            ((InConvalida) stato).approva();
        }

        stato.pubblica();
    }

    /**
     * Cambia lo stato del pacchetto in convalida in base al valore di esito.
     *
     * @param id Identificatore del pacchetto in convalida
     * @param esito Esito della convalida
     *
     * @throws ProdottoNonTrovatoException se l'id non corrisponde a nessun pacchetto
     * @throws DatiIncorrettiException se il pacchetto non è pronto alla convalida
     */
    public void approvaPacchetto(int id, boolean esito) {
        if (!repoPacchetti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste pacchetto con id " + id);

        Pacchetto pacchetto = repoPacchetti.get(id);
        StatoApprovazione stato = pacchetto.getStato();

        if (!(stato instanceof InConvalida)) throw new DatiIncorrettiException(
                "Non si può approvare un pacchetto non in stato di convalida");

        for (Prodotto p : pacchetto.getListaProdotti()) {
            if (p.getStato() instanceof Bozza) throw new DatiIncorrettiException(
                    "Il prodotto " + p.getNome() + " è una bozza, dunque il pacchetto non approvabile.");
        }

        //accettazione
        if (esito) {
            ((InConvalida) stato).approva();

            for (Prodotto p : pacchetto.getListaProdotti()) {
                if (p.getStato() instanceof InConvalida) {
                    ((InConvalida) p.getStato()).approva();
                }

            }

        }

        //pubblicazione
        for (Prodotto p : pacchetto.getListaProdotti()) {
            p.getStato().pubblica();
        }
        stato.pubblica();

    }

}
