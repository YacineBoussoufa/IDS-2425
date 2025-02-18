package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OSMService {

    //todo database
    public static Map<Integer, Prodotto> repoProdotti = new HashMap<>();
    public static Map<String, Venditore> repoVenditori = new HashMap<>();
    public static Map<Integer, Visita> repoVisite = new HashMap<>();
    public static Map<Integer, Manifestazione> repoManifestazioni = new HashMap<>();

    /**
     * Restituisce tutti i POI della piattaforma
     *
     * @return Tutti i POI della piattaforma
     */
    public List<POI> visualizzaMappa() {
        List<POI> poiList = new ArrayList<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            poiList.add(prodotto.getPoi());
        }

        for (Venditore venditore : repoVenditori.values()) {
            poiList.add(venditore.getLocalizzazione());
        }

        for (Visita visita : repoVisite.values()) {
            poiList.add(visita.getPuntoDiInteresse());
        }

        for (Manifestazione manifestazione : repoManifestazioni.values()) {
            poiList.add(manifestazione.getPuntoDiInteresse());
        }

        return poiList;
    }

    /**
     * Visualizza il POI di un venditore
     *
     * @param nome Username del venditore
     * @return POI del venditore
     *
     * @throws VenditoreNonTrovatoException se il nome non è associato a nessun venditore
     */
    public POI visualizzaVenditore(String nome) {
        if (!repoVenditori.containsKey(nome)) {
            throw new VenditoreNonTrovatoException();
        } else {
            return repoVenditori.get(nome).getLocalizzazione();
        }
    }

    /**
     * Visualizza il POI di tutti i venditori della piattaforma
     *
     * @return Tutti i POI dei venditori
     */
    public List<POI> visualizzaVenditori() {
        List<POI> poiList = new ArrayList<>();

        for (Venditore venditore : repoVenditori.values()) {
            poiList.add(venditore.getLocalizzazione());
        }

        return poiList;
    }

    /**
     * Visualizza il POI di un prodotto
     *
     * @param id Identificatore del prodotto
     * @return POI del prodotto
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a nessun prodotto
     */
    public POI visualizzaProdotto(int id) {
        if (!repoProdotti.containsKey(id)) {
            throw new ProdottoNonTrovatoException();
        } else {
            return repoProdotti.get(id).getPoi();
        }
    }

    /**
     * Visualizza i POI di tutti i prodotti della piattaforma
     *
     * @return Tutti i POI dei prodotti
     */
    public List<POI> visualizzaProdotti() {
        List<POI> poiList = new ArrayList<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            poiList.add(prodotto.getPoi());
        }

        return poiList;
    }

    /**
     * Visualizza tutti i POI delle visite
     *
     * @param id Identificatore della visita
     * @return POI della visita
     *
     * @throws EventoNonTrovatoException se l'id non è associato a nessuna visita
     */
    public POI visualizzaVisita(int id) {
        if (!repoVisite.containsKey(id)) {
            throw new EventoNonTrovatoException();
        } else {
            return repoVisite.get(id).getPuntoDiInteresse();
        }
    }

    /**
     * Visualizza tutti i POI delle visite nella piattaforma
     *
     * @return Tutti i POI delle visite
     */
    public List<POI> visualizzaVisite() {
        List<POI> poiList = new ArrayList<>();

        for (Visita visita : repoVisite.values()) {
            poiList.add(visita.getPuntoDiInteresse());
        }

        return poiList;
    }

    /**
     * Visualizza il POI di una manifestazione
     *
     * @param id Identificatore della manifestazione
     * @return POI della manifestazione
     *
     * @throws EventoNonTrovatoException se l'id non è associato a nessuna manifestazione
     */
    public POI visualizzaManifestazione(int id) {
        if (!repoManifestazioni.containsKey(id)) {
            throw new EventoNonTrovatoException();
        } else {
            return repoManifestazioni.get(id).getPuntoDiInteresse();
        }
    }

    /**
     * Visualizza tutti i POI delle manifestazioni nella piattaforma
     *
     * @return Tutti i POI delle manifestazioni
     */
    public List<POI> visualizzaManifestazioni() {
        List<POI> poiList = new ArrayList<>();

        for (Manifestazione manifestazione : repoManifestazioni.values()) {
            poiList.add(manifestazione.getPuntoDiInteresse());
        }

        return poiList;
    }

    /**
     * Cerca tutti gli oggetti legati a un POI
     *
     * @param poi POI da cercare
     * @return Oggetti legati al POI
     */
    public List<Object> trovaPOI(POI poi) {

        List<Object> poiList = new ArrayList<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            if (prodotto.getPoi().equals(poi)) {
                poiList.add(prodotto);
            }
        }

        for (Venditore venditore : repoVenditori.values()) {
            if (venditore.getLocalizzazione().equals(poi)) {
                poiList.add(venditore.getLocalizzazione());
            }
        }

        for (Visita visita : repoVisite.values()) {
            if (visita.getPuntoDiInteresse().equals(poi)) {
                poiList.add(visita.getPuntoDiInteresse());
            }
        }

        for (Manifestazione manifestazione : repoManifestazioni.values()) {
            if (manifestazione.getPuntoDiInteresse().equals(poi)) {
                poiList.add(manifestazione.getPuntoDiInteresse());
            }
        }

        return poiList;
    }

}
