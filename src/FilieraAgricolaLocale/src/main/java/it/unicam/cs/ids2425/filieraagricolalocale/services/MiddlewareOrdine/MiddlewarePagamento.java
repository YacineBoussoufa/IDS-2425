package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;

public class MiddlewarePagamento extends MiddlewareOrdine {

   public boolean inviaPagamento(String numeroCarta, Date dataScadenza, int cvv){
      return true;
   }

   @Override
   public boolean check(Date dataCreazione, Map<Prodotto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m) {

      if(!(inviaPagamento(m.getNumeroCarta(), m.getDataScadenza(), m.getCvv()))){
         return false;
      }
      return checkNext(dataCreazione, mappaProdotti, u, i, m);
   }
   
}
