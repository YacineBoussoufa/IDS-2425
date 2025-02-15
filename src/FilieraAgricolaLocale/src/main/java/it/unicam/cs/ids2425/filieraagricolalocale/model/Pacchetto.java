package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;
import java.util.Set;

public class Pacchetto implements Contenuto {

   private final int id;
   private String nome;
   private String descrizione;
   private double prezzo;
   private Set<Prodotto> listaProdotti;
   private Date data;
   private StatoApprovazione statoApprovazione;
   private Venditore venditore;
   private int quantita;

   public Pacchetto(String nome, String descrizione, double prezzo, Set<Prodotto> listaProdotti, Date data, Venditore v) {
      this.nome = nome;
      this.descrizione = descrizione;
      this.prezzo = prezzo;
      this.listaProdotti = listaProdotti;
      this.data = data;
      this.statoApprovazione = new Bozza(this);
      this.venditore = v;
      this.id = 0;
   }

   @Override
   public int getId() {
      return id;
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

   public Set<Prodotto> getListaProdotti() {
      return listaProdotti;
   }

   public void aggiungiProdotto(Prodotto p){
      this.listaProdotti.add(p);
   }
   
   public void rimuoviProdotto(Prodotto p){
      this.listaProdotti.remove(p);
   }

   public Venditore getVenditore() {
      return venditore;
   }

   public void setVenditore(Venditore venditore) {
      this.venditore = venditore;
   }

   @Override
   public int getQuantita() {
      return this.quantita;
   }

   public void setQuantita(int q) {
      this.quantita = q;
   }
}
