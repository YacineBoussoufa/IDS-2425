package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Persona;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class MiddlewarePagamento extends MiddlewareOrdine {

   public boolean inviaPagamento(String numeroCarta, Date dataScadenza, int cvv){
      return true;
   }

   @Override
   public boolean check(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i) {

      Persona p = (Persona) u;
      if(!(inviaPagamento(p.getNumeroCarta(), p.getDataScadenza(), p.getCvv()))){
         return false;
      }
      return checkNext(dataCreazione, mappaProdotti, u, i);
   }
   
}
