package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ContenutoRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VenditoreRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdottoService {


    //todo correttezza venditore
    private final VenditoreRepository repoVenditori;
    private final ContenutoRepository repoProdotti;
    private MiddlewareProdotto middlewareHead;


    @Autowired
    public ProdottoService(ContenutoRepository repoProdotti, VenditoreRepository repoVenditori) {
        this.repoProdotti = repoProdotti;
        this.repoVenditori = repoVenditori;
    }

    public void setMiddleware(MiddlewareProdotto m){
      this.middlewareHead = m;
    }

    private void controlloAutorizzazione(Contenuto contenuto, Account venditore) {
        if (!contenuto.getVenditore().equals(venditore)) {
            throw new NonAutorizzatoException("Il venditore " + venditore.getUsername() + " non può fare operazioni su " +
                    contenuto.getNome());
        }
    }

    /**
     * Crea un contenuto e controlla i dati inseriti.
     *
     * @param contenuto Contenuto da aggiungere
     *
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    @Transactional
    public void creaContenuto(Contenuto contenuto, Account venditore) {

        controlloAutorizzazione(contenuto, venditore);

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
    public void modificaContenuto(int id, Contenuto modifiche, Account venditore) {
        Contenuto attuale = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        //tutti gli elementi non vuoti (eccetto id e venditore) sono modificati
        Contenuto nuovo = attuale.setModifiche(modifiche);

        //controllo dati
        if (!middlewareHead.check(nuovo)) {
            throw new DatiIncorrettiException("I dati modificati non sono accettabili.");
        }

        controlloAutorizzazione(nuovo, venditore);

        //per impedire la duplicazione
        this.eliminaContenuto(id, venditore);
        repoProdotti.save(nuovo);
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
    public void restock(int id, int quantita, Account venditore) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        controlloAutorizzazione(contenuto, venditore);

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
    public void eliminaContenuto(int id, Account venditore) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        controlloAutorizzazione(contenuto, venditore);

        repoProdotti.delete(contenuto);
    }

    public String generaLinkSocial(String site, int id) {
        if (repoProdotti.findById(id).isPresent()) {
            return Social.generateLink(site, id);
        } else throw new ProdottoNonTrovatoException("Non esiste contenuto con id " + id);
    }

}
