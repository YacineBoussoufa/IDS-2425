package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ContenutoRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdottoService {

    private final ContenutoRepository repoProdotti;
    private MiddlewareProdotto middlewareHead;


    @Autowired
    public ProdottoService(ContenutoRepository repoProdotti) {
        this.repoProdotti = repoProdotti;
    }

    public void setMiddleware(MiddlewareProdotto m){
      this.middlewareHead = m;
    }

    /**
     * Crea un contenuto e controlla i dati inseriti.
     *
     * @param contenuto Contenuto da aggiungere
     *
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    @Transactional
    public void creaContenuto(Contenuto contenuto) {

        if (!middlewareHead.check(contenuto)) {
            throw new DatiIncorrettiException("I dati inseriti non sono accettabili.");
        }

       repoProdotti.save(contenuto);

    }

    /**
     * Modifica un contenuto a partire da un builder.
     * Setterà tutti i campi non vuoti con i campi inseriti dentro il builder.
     * Modificherà poi stato del prodotto in Bozza affinchè si proceda alla convalida delle modifiche.
     *
     * @param id ID del contenuto da modificare
     * @param modifiche Contenuto che contiene solo i campi da modificare
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    @Transactional
    public void modificaContenuto(int id, Contenuto modifiche) {
        Contenuto attuale = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        //tutti gli elementi non vuoti (eccetto id e venditore) sono modificati
        attuale.setModifiche(modifiche);

        //controllo dati
        if (!middlewareHead.check(attuale)) {
            throw new DatiIncorrettiException("I dati modificati non sono accettabili.");
        }

        repoProdotti.save(attuale);
    }

    /**
     * Metodo per aggiungere scorte ad un Contenuto.
     *
     * @param id Identificatore del contenuto
     * @param quantita Quantità da aggiungere al contenuto, sommata a quella attuale
     *
     * @throws ProdottoNonTrovatoException Se l'id non corrisponde a un Contenuto nel sistema.
     */
    @Transactional
    public void restock(int id, int quantita) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        int quantitaAttuale = contenuto.getQuantita();
        contenuto.setQuantita(quantitaAttuale + quantita);
        repoProdotti.save(contenuto);

    }

    /**
     * Elimina un contenuto con il suo id.
     *
     * @param id Identificatore contenuto
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un contenuto nel database
     */
    @Transactional
    public void eliminaContenuto(int id) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        repoProdotti.delete(contenuto);
    }

    public String generaLinkSocial(String site, int id) {
        if (repoProdotti.findById(id).isPresent()) {
            return Social.generateLink(site, id);
        } else throw new ProdottoNonTrovatoException("Non esiste contenuto con id " + id);
    }

}
