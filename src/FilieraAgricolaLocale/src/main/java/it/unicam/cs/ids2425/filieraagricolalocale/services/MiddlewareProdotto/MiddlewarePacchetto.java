package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pacchetto;

public class MiddlewarePacchetto extends MiddlewareProdotto {

   @Override
   public boolean check(Contenuto contenuto) {

      if(contenuto instanceof Pacchetto){

         Pacchetto p = (Pacchetto) contenuto;

         if(p.getListaProdotti().size() < 2) return false;

      }

      return checkNext(contenuto);
   }
}
