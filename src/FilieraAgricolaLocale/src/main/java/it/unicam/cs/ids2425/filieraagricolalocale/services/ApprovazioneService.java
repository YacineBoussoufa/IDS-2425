package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ContenutoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("ApprovazioneService")
public class ApprovazioneService {

    private final ContenutoRepository repoProdotti;

    public ApprovazioneService(ContenutoRepository repoProdotti) {
        this.repoProdotti = repoProdotti;
    }

    /**
     * Invia la richiesta per convalidare un prodotto, settando lo stato in InConvalida
     *
     * @param id Identificatore del prodotto
     *
     * @throws ProdottoNonTrovatoException se l'id non corrisponde a nessun prodotto nel database
     */
    @Transactional
    public void inviaRichiesta(int id) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        if (contenuto.getStato() instanceof Bozza) {
            contenuto.getStato().pubblica();
        }

        repoProdotti.save(contenuto);
        //necessario per modificare su database lo stato di approvazione dei prodotti nei pacchetti
        if (contenuto instanceof Pacchetto pacchetto) {
            repoProdotti.saveAll(pacchetto.getListaProdotti());
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
    public void approva(int id, boolean esito) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));
        StatoApprovazione stato = contenuto.getStato();

        if (!(stato instanceof InConvalida)) throw new DatiIncorrettiException(
                "Non si può approvare un prodotto non in stato di convalida");

        if (esito) {
            contenuto.approva();
        }

        stato.pubblica();

        repoProdotti.save(contenuto);
        //necessario per modificare su database lo stato di approvazione dei prodotti nei pacchetti
        if (contenuto instanceof Pacchetto pacchetto) {
            repoProdotti.saveAll(pacchetto.getListaProdotti());
        }
    }

}
