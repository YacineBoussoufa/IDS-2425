package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ordine {
   
   private final DateFormat dataCreazione;
   private boolean evaso;
   private Date dataDiConsegna; // stimata
   private final List<LineaOrdine> articoli;
   private Account user;
   private Indirizzo indirizzo;

   public Ordine(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Account u, Indirizzo i) {
      this.dataCreazione = dataCreazione;
      this.articoli = new LinkedList<>();
      for (Prodotto p : mappaProdotti.keySet()) {
         this.articoli.add(new LineaOrdine(p, this, mappaProdotti.get(p)));
      }
      this.evaso = false;
      this.user = u;
      this.indirizzo = i;
      this.dataDiConsegna = Date.from(Instant.now().plus(Duration.ofDays(7)));
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

   public Account getUser() {
      return user;
   }

   public void setUser(Account user) {
      this.user = user;
   }

   public DateFormat getDataCreazione() {
      return dataCreazione;
   }

   public boolean isEvaso() {
      return evaso;
   }
   public void setEvaso(boolean evaso) {
      this.evaso = evaso;
   }

   // this is a composite pattern, take note
   public double getPrezzoTotale() {
      double prezzoTotale = 0;
      for (LineaOrdine art : articoli) {
         prezzoTotale += art.getPrezzo();
      }
      return prezzoTotale;
   }

   public List<LineaOrdine> getArticoli() {
      return articoli;
   }
   
}
