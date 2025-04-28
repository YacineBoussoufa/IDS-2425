package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;
import java.util.Set;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;

public class PacchettoDTO {
       
   private int id;
   private String nome;
   private String descrizione;
   private double prezzo;
   private int quantita;
   private Venditore venditore;
   private Date data;
   private Set<Integer> listaProdotti;
   public PacchettoDTO(int id, String nome, String descrizione, double prezzo, int quantita, Venditore venditore,
         Date data, Set<Integer> listaProdotti) {
      this.id = id;
      this.nome = nome;
      this.descrizione = descrizione;
      this.prezzo = prezzo;
      this.quantita = quantita;
      this.venditore = venditore;
      this.data = data;
      this.listaProdotti = listaProdotti;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
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
   public int getQuantita() {
      return quantita;
   }
   public void setQuantita(int quantita) {
      this.quantita = quantita;
   }
   public Venditore getVenditore() {
      return venditore;
   }
   public void setVenditore(Venditore venditore) {
      this.venditore = venditore;
   }
   public Date getData() {
      return data;
   }
   public void setData(Date data) {
      this.data = data;
   }
   public Set<Integer> getListaProdotti() {
      return listaProdotti;
   }
   public void setListaProdotti(Set<Integer> listaProdotti) {
      this.listaProdotti = listaProdotti;
   }

}
