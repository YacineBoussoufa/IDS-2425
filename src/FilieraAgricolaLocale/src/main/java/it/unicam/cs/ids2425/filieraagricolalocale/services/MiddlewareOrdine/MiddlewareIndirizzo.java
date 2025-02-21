package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;

public class MiddlewareIndirizzo extends MiddlewareOrdine {

   @Override
   public boolean check(Ordine o) {
      if(!(o.getIndirizzo().getCAP().length() == 5 && o.getIndirizzo().getProvincia().length() == 2)){
         return false;
      }
      return checkNext(o);
   }
   
}
