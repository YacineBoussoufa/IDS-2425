package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

public class MiddlewareIndirizzo extends MiddlewareOrdine {

   @Override
   public boolean check(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m) {
      if(!(i.getCAP().length() == 5 && i.getProvincia().length() == 2)){
         return false;
      }
      return checkNext(dataCreazione, mappaProdotti, u, i, m);
   }
   
}
