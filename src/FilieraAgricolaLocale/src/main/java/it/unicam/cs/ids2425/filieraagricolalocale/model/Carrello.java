package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrello {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

   private Date ultimaModifica;

   @ManyToOne(cascade = CascadeType.ALL)
   @JsonBackReference
   private Utente proprietario;

   @OneToMany(cascade = CascadeType.ALL)
   @JsonBackReference
   private List<SlotCarrello> contenutoCarrello;
   

   public Carrello(Utente proprietario) {
      this.ultimaModifica = Date.from(Instant.now());
      this.proprietario = proprietario;
      this.contenutoCarrello = new LinkedList<>();
   }

   public Carrello() {
   }

   public Date getUltimaModifica() {
      return ultimaModifica;
   }

   public void setUltimaModifica(Date ultimaModifica) {
      this.ultimaModifica = ultimaModifica;
   }

   public void aggiungiContenuto(Contenuto c, int quantita){
      this.contenutoCarrello.add(new SlotCarrello(c, quantita, this));
   }
   
   public void rimuoviContenuto(Contenuto c){
      for (SlotCarrello slotCarrello : contenutoCarrello) {
         if(slotCarrello.getProdotto().equals(c)) {this.contenutoCarrello.remove(slotCarrello);}
      }
   }

   public List<SlotCarrello> getCarrello(){
      return this.contenutoCarrello;
   }

   public void modificaQuantita(Contenuto c, int q){
      for (SlotCarrello slotCarrello : contenutoCarrello) {
         if(slotCarrello.getProdotto().equals(c)) {slotCarrello.setQuantita(q);}
      }
   }

   public Utente getProprietario() {
      return proprietario;
   }

   public void svuota(){
      this.contenutoCarrello.clear();
   }

}
