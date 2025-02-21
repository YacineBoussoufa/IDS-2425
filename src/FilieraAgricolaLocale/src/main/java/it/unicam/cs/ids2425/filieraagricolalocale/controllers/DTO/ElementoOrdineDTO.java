package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

public class ElementoOrdineDTO {
   
   private int quantita;
   private Contenuto p;
   private int id;

 
   public ElementoOrdineDTO(int quantita, Contenuto p, int id) {
      this.quantita = quantita;
      this.p = p;
      this.id = id;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
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
