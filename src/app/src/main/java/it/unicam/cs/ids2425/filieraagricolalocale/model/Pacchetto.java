package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;
import java.util.Set;

public class Pacchetto implements Contenuto {

   private String nome;
   private String descrizione;
   private double prezzo;
   private Set<Prodotto> listaProdotti;
   private Date data;
   private StatoApprovazione statoApprovazione;

   public Pacchetto(String nome, String descrizione, double prezzo, Set<Prodotto> listaProdotti, Date data) {
      this.nome = nome;
      this.descrizione = descrizione;
      this.prezzo = prezzo;
      this.listaProdotti = listaProdotti;
      this.data = data;
      this.statoApprovazione = new Bozza(this);
   }

   public Date getData() {
      return data;
   }

   public void setData(Date data) {
      this.data = data;
   }

   @Override
   public StatoApprovazione getStato() {
      return statoApprovazione;
   }

   @Override
   public void cambiaStato(StatoApprovazione stato) {
      this.statoApprovazione = stato;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getDescrizione() {
      return descrizione;
   }

   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }

   public double getPrezzo() {
      return prezzo;
   }

   public void setPrezzo(double prezzo) {
      this.prezzo = prezzo;
   }

   public void aggiungiProdotto(Prodotto p){
      this.listaProdotti.add(p);
   }
   
   public void rimuoviProdotto(Prodotto p){
      this.listaProdotti.remove(p);
   }
}
