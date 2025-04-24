package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;

public class MiddlewareCarrello extends MiddlewareOrdine {

   @Override
   public boolean check(Ordine o) {

      if(o.getArticoli().size() < 1) return false;

      return checkNext(o);
   }
   
}
