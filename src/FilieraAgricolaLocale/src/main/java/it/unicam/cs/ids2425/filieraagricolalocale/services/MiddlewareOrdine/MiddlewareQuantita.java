package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;

public class MiddlewareQuantita extends MiddlewareOrdine {

   private MarketplaceService marketplaceService;

   public MiddlewareQuantita(MarketplaceService p){
      this.marketplaceService = p;
   }

   @Override
  
   public boolean check(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m) {
      for (Contenuto p : mappaProdotti.keySet()) {
        
         if(marketplaceService.visualizzaProdotto(p.getId()).getQuantita() < mappaProdotti.get(p))
            return false;

         int q = marketplaceService.visualizzaProdotto(p.getId()).getQuantita();
         marketplaceService.visualizzaProdotto(p.getId()).setQuantita(q - mappaProdotti.get(p));
      }
      return checkNext(dataCreazione, mappaProdotti, u, i, m);
   }
   
}
