package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;

public abstract class MiddlewareUtente {

   private MiddlewareUtente next;

   public static MiddlewareUtente link(MiddlewareUtente first, MiddlewareUtente... chain) {
      MiddlewareUtente head = first;
      for (MiddlewareUtente nextInChain: chain) {
          head.next = nextInChain;
          head = nextInChain;
      }
      return first;
   }

   public abstract boolean check(Account u);

   public boolean checkNext(Account u) {
      if(next == null){
         return true;
      }
      return next.check(u);
   }

}