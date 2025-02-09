package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine;

import java.text.DateFormat;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class MiddlewareQuantita extends MiddlewareOrdine {

   private ProdottoService prodottoService;

   public MiddlewareQuantita(ProdottoService p){
      this.prodottoService = p;
   }

   @Override
   public boolean check(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i) {
      for (Prodotto p : mappaProdotti.keySet()) {
         if(prodottoService.get(p).getQuantita() < mappaProdotti.get(p))
            return false;
      }
      return checkNext(dataCreazione, mappaProdotti, u, i);
   }
   
}
