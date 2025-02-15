package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OSMService {

    //todo database
    public static Map<Integer, Prodotto> repoProdotti = new HashMap<>();
    public static Map<String, Venditore> repoVenditori = new HashMap<>();

    public Set<POI> visualizzaMappa() {
        Set<POI> poiSet = new HashSet<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            poiSet.add(prodotto.getPoi());
        }

        for (Venditore venditore : repoVenditori.values()) {
            poiSet.add(venditore.getLocalizzazione());
        }

        return poiSet;
    }

    public POI visualizzaVenditore(String nome) {
        if (!repoVenditori.containsKey(nome)) {
            throw new VenditoreNonTrovatoException();
        } else {
            return repoVenditori.get(nome).getLocalizzazione();
        }
    }

    public Set<POI> visualizzaVenditori() {
        Set<POI> poiSet = new HashSet<>();

        for (Venditore venditore : repoVenditori.values()) {
            poiSet.add(venditore.getLocalizzazione());
        }

        return poiSet;
    }

    public POI visualizzaProdotto(int id) {
        if (!repoProdotti.containsKey(id)) {
            throw new ProdottoNonTrovatoException();
        } else {
            return repoProdotti.get(id).getPoi();
        }
    }

    public Set<POI> visualizzaProdotti() {
        Set<POI> poiSet = new HashSet<>();

        for (Prodotto prodotto : repoProdotti.values()) {
            poiSet.add(prodotto.getPoi());
        }

        return poiSet;
    }
}
