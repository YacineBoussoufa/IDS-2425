package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ordine {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private Date dataCreazione;
   private Date dataDiConsegna; // stimata

   @OneToMany(cascade = CascadeType.ALL)
   @JsonManagedReference
   private List<LineaOrdine> lineeOrdine;

   @ManyToOne
   private Utente user;

   @OneToOne(cascade = CascadeType.ALL)
   private Indirizzo indirizzo;

   @OneToOne(cascade = CascadeType.ALL)
   private Pagamento metodo;

   public Ordine(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Utente u, Indirizzo i, Pagamento m) {
      this.dataCreazione = dataCreazione;
      this.lineeOrdine = new LinkedList<>();
      //TODO check this (maybe outside in the service)
      for (Contenuto p : mappaProdotti.keySet()) {
         this.lineeOrdine.add(new LineaOrdine(p, this, mappaProdotti.get(p)));
      }
      this.user = u;
      this.indirizzo = i;
      this.dataDiConsegna = Date.from(Instant.now().plus(Duration.ofDays(7)));
      this.metodo = m;
   }

   public Ordine () {

   }

   public Pagamento getMetodo() {
      return metodo;
   }

   public Date getDataDiConsegna() {
      return dataDiConsegna;
   }

   public void setDataDiConsegna(Date dataDiConsegna) {
      this.dataDiConsegna = dataDiConsegna;
   }

   public Indirizzo getIndirizzo() {
      return indirizzo;
   }

   public void setIndirizzo(Indirizzo indirizzo) {
      this.indirizzo = indirizzo;
   }

   public Utente getUser() {
      return user;
   }

   public void setUser(Utente user) {
      this.user = user;
   }

   public Date getDataCreazione() {
      return dataCreazione;
   }

   // this is a composite pattern, take note
   public double getPrezzoTotale() {
      double prezzoTotale = 0;
      for (LineaOrdine art : lineeOrdine) {
         prezzoTotale += art.getPrezzo();
      }
      return prezzoTotale;
   }

   public List<LineaOrdine> getArticoli() {
      return lineeOrdine;
   }
   
}
