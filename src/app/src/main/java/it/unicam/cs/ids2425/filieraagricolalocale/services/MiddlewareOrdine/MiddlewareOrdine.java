package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.text.DateFormat;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

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

   public abstract boolean check(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i);

   public boolean checkNext(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i) {
      if(next == null){
         return true;
      }
      return next.check(dataCreazione, mappaProdotti, u, i);
   }

}
