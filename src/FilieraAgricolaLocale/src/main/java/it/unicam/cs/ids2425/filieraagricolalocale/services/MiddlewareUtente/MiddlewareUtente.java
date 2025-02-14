package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente;

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

   public abstract boolean check();

   public boolean checkNext() {
      if(next == null){
         return true;
      }
      return next.check();
   }

}