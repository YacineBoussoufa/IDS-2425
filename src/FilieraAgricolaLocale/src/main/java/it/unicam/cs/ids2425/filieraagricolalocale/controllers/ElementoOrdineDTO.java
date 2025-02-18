package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

public class ElementoOrdineDTO {
   
   private int quantita;
   private Contenuto p;
   
   public ElementoOrdineDTO(int quantita, Contenuto p) {
      this.quantita = quantita;
      this.p = p;
   }
   public int getQuantita() {
      return quantita;
   }
   public void setQuantita(int quantita) {
      this.quantita = quantita;
   }
   public Contenuto getP() {
      return p;
   }
   public void setP(Contenuto p) {
      this.p = p;
   }

   

}
