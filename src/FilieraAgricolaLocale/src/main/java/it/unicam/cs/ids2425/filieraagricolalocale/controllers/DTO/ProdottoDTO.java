package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;
import java.util.Set;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Etichetta;

public class ProdottoDTO {
       
   private int id;
   private String nome;
   private String descrizione;
   private double prezzo;
   private int quantita;
   private String venditore;
   private Date data;
   private Set<Integer> ingredienti;
   private Set<Etichetta> listaEtichette;

   public ProdottoDTO(int id, String nome, String descrizione, double prezzo, int quantita, String venditore,
         Date data, Set<Integer> ingredienti, Set<Etichetta> listaEtichetta) {
      this.id = id;
      this.nome = nome;
      this.descrizione = descrizione;
      this.prezzo = prezzo;
      this.quantita = quantita;
      this.venditore = venditore;
      this.data = data;
      this.ingredienti = ingredienti;
      this.listaEtichette = listaEtichetta;
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
   public String getVenditore() {
      return venditore;
   }
   public void setVenditore(String venditore) {
      this.venditore = venditore;
   }
   public Date getData() {
      return data;
   }
   public void setData(Date data) {
      this.data = data;
   }
   public Set<Integer> getIngredienti() {
      return ingredienti;
   }
   public void setIngredienti(Set<Integer> ingredienti) {
      this.ingredienti = ingredienti;
   }
   public Set<Etichetta> getListaEtichette() {
      return listaEtichette;
   }
   public void setListaEtichette(Set<Etichetta> listaEtichetta) {
      this.listaEtichette = listaEtichetta;
   }



}
