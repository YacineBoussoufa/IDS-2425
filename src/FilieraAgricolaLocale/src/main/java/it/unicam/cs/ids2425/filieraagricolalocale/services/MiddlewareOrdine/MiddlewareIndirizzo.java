package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.text.DateFormat;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;

public class MiddlewareIndirizzo extends MiddlewareOrdine {

   @Override
   public boolean check(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Account u, Indirizzo i) {
      if(!(i.getCAP().length() == 5 && i.getProvincia().length() == 2)){
         return false;
      }
      return checkNext(dataCreazione, mappaProdotti, u, i);
   }
   
}
