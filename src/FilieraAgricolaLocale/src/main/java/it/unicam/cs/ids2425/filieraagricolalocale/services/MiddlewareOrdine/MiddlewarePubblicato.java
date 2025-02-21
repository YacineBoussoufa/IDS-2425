package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import it.unicam.cs.ids2425.filieraagricolalocale.model.LineaOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Stato;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;

public class MiddlewarePubblicato extends MiddlewareOrdine {

   private MarketplaceService marketplaceService;

   public MiddlewarePubblicato(MarketplaceService p){
      this.marketplaceService = p;
   }

   @Override
  
   public boolean check(Ordine o) {
      for (LineaOrdine p : o.getArticoli()) {
        
         if(marketplaceService.visualizzaContenuto(p.getProdotto().getId()).getStato().statoToString() != Stato.PUBBLICATO)
            return false;

      }
      return checkNext(o);
   }
   
}
