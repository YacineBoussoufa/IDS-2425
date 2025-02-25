package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

public class ElementoOrdineDTO {
   
   private int quantita;
   private int id;

 
   public ElementoOrdineDTO(int quantita, int id) {
      this.quantita = quantita;
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

   

}
