package it.unicam.cs.ids2425.filieraagricolalocale;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ordine {
   
   private final DateFormat dataCreazione;
   private boolean evaso;
   private double prezzoTotale;
   private final List<LineaOrdine> articoli;


   public Ordine(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti) {
      this.dataCreazione = dataCreazione;
      this.articoli = new LinkedList<>();
      for (Prodotto p : mappaProdotti.keySet()) {
         this.articoli.add(new LineaOrdine(p, this, mappaProdotti.get(p)));
      }
      this.evaso = false;
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
