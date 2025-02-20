package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import it.unicam.cs.ids2425.filieraagricolalocale.repository.ContenutoRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VenditoreRepository;
import org.springframework.stereotype.Component;

@Component("MarketplaceService")
public class MarketplaceService {

    private final ContenutoRepository repoProdotti;
    private final VenditoreRepository repoVenditori;

    public MarketplaceService(ContenutoRepository repoProdotti, VenditoreRepository repoVenditori) {
        this.repoProdotti = repoProdotti;
        this.repoVenditori = repoVenditori;
    }

    /**
     * Restituisce un contenuto a partire dal suo id.
     *
     * @param id Identificatore contenuto
     * @return Prodotto, se nel database
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un contenuto nel database
     */
    public Contenuto visualizzaContenuto(int id) {
        Contenuto contenuto = repoProdotti.findById(id).
                orElseThrow(() -> new ProdottoNonTrovatoException("Non esiste contenuto con id " + id));

        return contenuto;
    }

    /**
     * Restituisce un venditore a partire dal suo username
     *
     * @param username Username venditore
     * @return Venditore, se nel database
     *
     * @throws VenditoreNonTrovatoException se l'username non è associato a un venditore nel database
     */
    public Venditore visualizzaVenditore(String username) {
        Venditore venditore = repoVenditori.findById(username).
                orElseThrow(() -> new VenditoreNonTrovatoException("Non esiste il venditore " + username));

        return venditore;
    }
    
    /**
     * Restituisce tutti i prodotti di un venditore.
     *
     * @param username Username del venditore
     * @return Lista prodotti del venditore
     *
     * @throws VenditoreNonTrovatoException se l'username non è associato a un venditore nel database
     */
    public List<Contenuto> visualizzaContenutiVenditore(String username) {
        Venditore venditore = repoVenditori.findById(username).
                orElseThrow(() -> new VenditoreNonTrovatoException("Non esiste il venditore " + username));

        return new ArrayList<>(repoProdotti.findByVenditoreUsername(username));
    }

    /**
     * Restituisce tutti i contenuti del marketplace.
     *
     * @return Tutti i contenuti
     */
    public List<Contenuto> visualizzaContenuti() {
        return new ArrayList<>(repoProdotti.findAll());
    }

    /**
     * Restituisce tutti i prodotti del marketplace.
     *
     * @return Tutti i prodotti
     */
    public List<Contenuto> visualizzaProdotti() {
        return new ArrayList<>(repoProdotti.findByTipo("PRODOTTO"));
    }

    /**
     * Restituisce tutti i pacchetti nel marketplace.
     *
     * @return Tutti i pacchetti
     */
    public List<Contenuto> visualizzaPacchetti() {
        return new ArrayList<>(repoProdotti.findByTipo("PACCHETTO"));
    }

    /**
     * Restituisce tutti i venditori nel marketplace.
     *
     * @return Tutti i venditori
     */
    public List<Venditore> visualizzaVenditori() {
        return new ArrayList<>(repoVenditori.findAll());
    }

    /**
     * Restituisce tutti i prodotti e pacchetti con uno stato di approvazione in comune.
     *
     * @param s Stato di approvazione da applicare come filtro
     * @return Tutti i contenuti con lo stato s
     */
    public List<Contenuto> visualizzaProdottiPerStato(Class<? extends StatoApprovazione> s) {
        List<Contenuto> contenuti = visualizzaContenuti();

        return contenuti.stream().
                filter(p -> p.getStato().getClass() == s).toList();
    }


}
