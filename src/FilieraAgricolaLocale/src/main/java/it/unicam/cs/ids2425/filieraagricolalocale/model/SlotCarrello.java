package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SlotCarrello {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   @ManyToOne
   private Contenuto prodotto;

   @ManyToOne
   @JsonBackReference
   private Carrello cart;

   private int quantita;

   public SlotCarrello(Contenuto prodotto, int quantita, Carrello cart) {
      this.prodotto = prodotto;
      this.cart = cart;
      this.quantita = quantita;
   }

   public SlotCarrello() {
   }

   public int getId() {
      return id;
   }

   public Contenuto getProdotto() {
      return prodotto;
   }

   public Carrello getCart() {
      return cart;
   }

   public int getQuantita() {
      return quantita;
   }

   public void setQuantita(int quantita) {
      this.quantita = quantita;
   }

   

}
