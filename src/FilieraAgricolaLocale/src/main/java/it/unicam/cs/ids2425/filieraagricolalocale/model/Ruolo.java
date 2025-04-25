package it.unicam.cs.ids2425.filieraagricolalocale.model;


public enum Ruolo {

   Curatore("Curatore"),
   Animatore("Animatore"),
   Gestore("Gestore"),
   Produttore("Produttore"),
   Trasformatore("Trasformatore"),
   Distributore("Distributore");

   private final String message;

   Ruolo(String message) {
       this.message = message;
   }

   public String getMessage() {
       return message;
   }

}
