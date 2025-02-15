package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

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

   public abstract boolean check(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m);

   public boolean checkNext(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m) {
      if(next == null){
         return true;
      }
      return next.check(dataCreazione, mappaProdotti, u, i, m);
   }

}
