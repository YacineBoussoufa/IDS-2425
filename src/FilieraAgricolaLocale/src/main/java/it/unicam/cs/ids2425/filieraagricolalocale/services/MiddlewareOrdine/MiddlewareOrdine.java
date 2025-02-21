package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;

public abstract class MiddlewareOrdine {

   private MiddlewareOrdine next;

   public static MiddlewareOrdine link(MiddlewareOrdine first, MiddlewareOrdine... chain) {
      MiddlewareOrdine head = first;
      for (MiddlewareOrdine nextInChain: chain) {
          head.next = nextInChain;
          head = nextInChain;
      }
      return first;
   }

  
   public abstract boolean check(Ordine o);

   public boolean checkNext(Ordine o) {
      if(next == null) {
         return true;
      }
      return next.check(o);
   }

}
