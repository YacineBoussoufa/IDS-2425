package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
   private final int id;
   private final Date dataCreazione;
   private Date dataDiConsegna; // stimata

   @OneToMany
   @JsonManagedReference
   private final List<LineaOrdine> lineeOrdine;

   @ManyToOne
   private Utente user;

   @OneToOne
   private Indirizzo indirizzo;

   @OneToOne
   private final Pagamento metodo;

   public Ordine(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Utente u, Indirizzo i, Pagamento m) {
      this.dataCreazione = dataCreazione;
      this.lineeOrdine = new LinkedList<>();
      for (Contenuto p : mappaProdotti.keySet()) {
         this.lineeOrdine.add(new LineaOrdine(p, this, mappaProdotti.get(p)));
      }
      this.user = u;
      this.indirizzo = i;
      this.dataDiConsegna = Date.from(Instant.now().plus(Duration.ofDays(7)));
      this.metodo = m;
      this.id = 0;
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
