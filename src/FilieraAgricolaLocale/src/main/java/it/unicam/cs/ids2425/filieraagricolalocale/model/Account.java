package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.List;

public sealed interface Account permits Utente, Venditore {
   
   /**
    * @return
    */
   List<Ruolo> getListaRuoli();

   /**
    * @return
    */
   String getUsername();

   /**
    *
    * @return
    */
   String getPassword();
}
