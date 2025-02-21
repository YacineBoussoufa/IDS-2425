package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.util.Date;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;


public class MiddlewarePagamento extends MiddlewareOrdine {

   public boolean inviaPagamento(String numeroCarta, Date dataScadenza, int cvv){
      return true;
   }

   @Override
   public boolean check(Ordine o) {

      if(!(inviaPagamento(o.getMetodo().getNumeroCarta(), o.getMetodo().getDataScadenza(), o.getMetodo().getCvv()))){
         return false;
      }
      return checkNext(o);
   }
   
}
