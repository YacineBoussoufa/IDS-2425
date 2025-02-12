package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.text.DateFormat;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;

public class MiddlewareQuantita extends MiddlewareOrdine {

   private MarketplaceService marketplaceService;

   public MiddlewareQuantita(MarketplaceService p){
      this.marketplaceService = p;
   }

   @Override
   public boolean check(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i) {
      for (Prodotto p : mappaProdotti.keySet()) {
         if(marketplaceService.visualizzaProdotto(p.getId()).getQuantita() < mappaProdotti.get(p))
            return false;
            //TODO diminuire quantita'
         //marketplaceService.visualizzaProdotto(p.getId()).se
      }
      return checkNext(dataCreazione, mappaProdotti, u, i);
   }
   
}
