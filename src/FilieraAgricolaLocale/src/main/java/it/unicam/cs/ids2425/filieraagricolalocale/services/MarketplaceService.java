package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pubblicato;
import it.unicam.cs.ids2425.filieraagricolalocale.model.StatoApprovazione;

import java.util.*;

import org.springframework.stereotype.Component;

@Component("MarketplaceService")
public class MarketplaceService {

    //todo MOCK DI UNA TABELLA PRODOTTI
    public static Map<Integer, Prodotto> repo = ProdottoService.repoProdotti;

    /**
     * Restituisce un prodotto a partire dal suo id.
     *
     * @param id Identificatore prodotto
     * @return Prodotto, se nel database
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     */
    public Prodotto visualizzaProdotto(int id) {
        if (!repo.containsKey(id)) throw new ProdottoNonTrovatoException();

        return repo.get(id);
    }
    
    /**
     * Restituisce tutti i prodotti di un venditore, bozze comprese.
     *
     * @param venditore Venditore
     * @return Lista prodotti del venditore
     */
    public List<Prodotto> visualizzaProdottiVenditore(Venditore venditore) {
        List<Prodotto> prodottiVenditore = new ArrayList<>();

        for (Prodotto prodotto : repo.values()) {
            if (prodotto.getVenditore().equals(venditore)) {
                prodottiVenditore.add(prodotto);
            }
        }

        return prodottiVenditore;
    }

    /**
     * Restituisce tutti i prodotti pubblicati, cioè quelli che hanno superato il processo di convalida.
     *
     * @return Prodotti pubblicati.
     */
    public List<Prodotto> visualizzaProdottiPubblicati() {
        List<Prodotto> prodottiVisibili = new ArrayList<>();

        for (Prodotto prodotto : repo.values()) {
            if (prodotto.getStato() instanceof Pubblicato) {
                prodottiVisibili.add(prodotto);
            }
        }

        return prodottiVisibili;
    }

    /**
     * Restituisce tutti i prodotti del marketplace, pubblicati o meno.
     *
     * @return Tutti i prodotti
     */
    public List<Prodotto> visualizzaProdotti() {
        return new ArrayList<>(repo.values());
    }

    /**
     * @param s
     * @return
     */
    public List<Prodotto> visualizzaProdottiStato(Class<? extends StatoApprovazione> s) {
      return repo.values().stream().filter(p -> p.getStato().getClass() == s).toList();
    }

}
