package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.util.*;

import org.springframework.stereotype.Component;

@Component("MarketplaceService")
public class MarketplaceService {

    //todo MOCK DI UNA TABELLA PRODOTTI
    public static Map<Integer, Prodotto> repoProdotti = new HashMap<>();
    public static Map<Integer, Pacchetto> repoPacchetti = new HashMap<>();
    public static Map<String, Venditore> repoVenditori = new HashMap<>();

    /**
     * Restituisce un prodotto a partire dal suo id.
     *
     * @param id Identificatore prodotto
     * @return Prodotto, se nel database
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     */
    public Prodotto visualizzaProdotto(int id) {
        if (!repoProdotti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste prodotto con id " + id);

        return repoProdotti.get(id);
    }

    /**
     * Restituisce un pacchetto a partire dal suo id
     *
     * @param id Identificatore pacchetto
     * @return Pacchetto, se nel database
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un pacchetto nel database
     */
    public Pacchetto visualizzaPacchetto(int id) {
        if (!repoPacchetti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste pacchetto con id " + id);

        return repoPacchetti.get(id);
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
        if (!repoVenditori.containsKey(username)) throw new VenditoreNonTrovatoException(
                "Non esiste venditore con l'username " + username);

        return repoVenditori.get(username);
    }
    
    /**
     * Restituisce tutti i prodotti di un venditore.
     *
     * @param username Username del venditore
     * @return Lista prodotti del venditore
     *
     * @throws VenditoreNonTrovatoException se l'username non è associato a un venditore nel database
     */
    public List<Prodotto> visualizzaProdottiVenditore(String username) {
        if (!repoVenditori.containsKey(username)) throw new VenditoreNonTrovatoException(
                "Non esiste venditore con l'username " + username);

        List<Prodotto> prodottiVenditore = new ArrayList<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            if (prodotto.getVenditore().getUsername().equals(username)) {
                prodottiVenditore.add(prodotto);
            }
        }

        return prodottiVenditore;
    }

    /**
     * Restituisce tutti i pacchetti di un venditore.
     *
     * @param username Username del venditore
     * @return Lista pacchetti del venditore
     */
    public List<Pacchetto> visualizzaPacchettiVenditore(String username) {
        if (!repoVenditori.containsKey(username)) throw new VenditoreNonTrovatoException(
                "Non esiste venditore con l'username " + username);

        List<Pacchetto> pacchettiVenditore = new ArrayList<>();

        for (Pacchetto pacchetto : repoPacchetti.values()) {
            if (pacchetto.getVenditore().getUsername().equals(username)) {
                pacchettiVenditore.add(pacchetto);
            }
        }

        return pacchettiVenditore;
    }

    /**
     * Restituisce tutti i prodotti del marketplace.
     *
     * @return Tutti i prodotti
     */
    public List<Prodotto> visualizzaProdotti() {
        return new ArrayList<>(repoProdotti.values());
    }

    /**
     * Restituisce tutti i pacchetti nel marketplace.
     *
     * @return Tutti i pacchetti
     */
    public List<Pacchetto> visualizzaPacchetti() {
        return new ArrayList<>(repoPacchetti.values());
    }

    /**
     * Restituisce tutti i venditori nel marketplace.
     *
     * @return Tutti i venditori
     */
    public List<Venditore> visualizzaVenditori() {
        return new ArrayList<>(repoVenditori.values());
    }

    /**
     * Restituisce tutti i prodotti e pacchetti con uno stato di approvazione in comune.
     *
     * @param s Stato di approvazione da applicare come filtro
     * @return Tutti i contenuti con lo stato s
     */
    public List<Contenuto> visualizzaProdottiPerStato(Class<? extends StatoApprovazione> s) {
        List<Contenuto> prodottiPerStato = new ArrayList<>();

        List<Prodotto> prodotti = repoProdotti.values().stream().
                filter(p -> p.getStato().getClass() == s).toList();

        List<Pacchetto> pacchetti = repoPacchetti.values().stream().
                filter(p -> p.getStato().getClass() == s).toList();

        prodottiPerStato.addAll(prodotti);
        prodottiPerStato.addAll(pacchetti);
        return prodottiPerStato;
    }


}
