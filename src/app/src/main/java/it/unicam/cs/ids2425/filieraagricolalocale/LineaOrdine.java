package it.unicam.cs.ids2425.filieraagricolalocale;

public class LineaOrdine {

   private final Prodotto prodotto;
   private final Ordine ordine;
   private int quantita;

   public LineaOrdine(Prodotto prodotto, Ordine ordine, int quantita) {
      this.prodotto = prodotto;
      this.ordine = ordine;
      this.quantita = quantita;
   }


   public double getPrezzo() {
      return this.prodotto.getPrezzo()*this.quantita;
   }


   public Prodotto getProdotto() {
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
