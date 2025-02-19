package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class LineaOrdine {

   private final Contenuto prodotto;
   @JsonBackReference
   private final Ordine ordine;
   private int quantita;

   public LineaOrdine(Contenuto prodotto, Ordine ordine, int quantita) {
      this.prodotto = prodotto;
      this.ordine = ordine;
      this.quantita = quantita;
   }


   public double getPrezzo() {
      return this.prodotto.getPrezzo()*this.quantita;
   }


   public Contenuto getProdotto() {
      return prodotto;
   }


   public Ordine getOrdine() {
      return ordine;
   }


   public int getQuantita() {
      return quantita;
   }


   public void setQuantita(int quantita) {
      this.quantita = quantita;
   }

}
