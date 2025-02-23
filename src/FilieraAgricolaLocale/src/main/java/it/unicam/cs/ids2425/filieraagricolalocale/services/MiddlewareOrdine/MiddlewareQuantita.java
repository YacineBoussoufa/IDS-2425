package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import it.unicam.cs.ids2425.filieraagricolalocale.model.LineaOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;

import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;

public class MiddlewareQuantita extends MiddlewareOrdine {

   private MarketplaceService marketplaceService;
   private ProdottoService prodottoService;

   public MiddlewareQuantita(MarketplaceService p, ProdottoService ps){
      this.marketplaceService = p;
      this.prodottoService = ps;
   }

   @Override
  
   public boolean check(Ordine o) {
      for (LineaOrdine p : o.getArticoli()) {

         if(marketplaceService.visualizzaContenuto(p.getProdotto().getId()).getQuantita() < p.getQuantita())
            return false;

         prodottoService.restock(p.getProdotto().getId(), -(p.getQuantita()), p.getProdotto().getVenditore());
      }
      return checkNext(o);
   }
   
}
