package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LineaOrdine {

   @Id
   private int id;

   @ManyToOne
   private Contenuto prodotto;

   @ManyToOne
   @JsonBackReference
   private Ordine ordine;

   private int quantita;

   public LineaOrdine(Contenuto prodotto, Ordine ordine, int quantita) {
      this.prodotto = prodotto;
      this.ordine = ordine;
      this.quantita = quantita;
   }

   public LineaOrdine() {
   }

   public int getId() {
      return id;
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
