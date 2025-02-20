package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
public class PacchettoBuilder implements ContenutoBuilder{

    
   private int id;
   private String nome;
   private String descrizione;
   private double prezzo = 0;
   private int quantita = 0;
   private Venditore venditore;
   private Date data;
   private Set<Prodotto> listaProdotti;

   public Pacchetto build() {
        return new Pacchetto(this);
   }

   public static PacchettoBuilder copiaDa(Pacchetto prodotto) {
      PacchettoBuilder builder = new PacchettoBuilder();
      builder.setId(prodotto.getId());
      builder.setNome(prodotto.getNome());
      builder.setDescrizione(prodotto.getDescrizione());
      builder.setPrezzo(prodotto.getPrezzo());
      builder.setQuantita(prodotto.getQuantita());
      builder.setVenditore(prodotto.getVenditore());
      builder.setData(prodotto.getData());
      builder.setListaProdotti(prodotto.getListaProdotti());
      return builder;
   }

   public int getId() {
      return id;
   }

   public PacchettoBuilder setId(int id) {
      this.id = id;
      return this;
   }

   public String getNome() {
      return nome;
   }

   public PacchettoBuilder setNome(String nome) {
      this.nome = nome;
      return this;
   }

   public String getDescrizione() {
      return descrizione;
   }

   public PacchettoBuilder setDescrizione(String descrizione) {
      this.descrizione = descrizione;
      return this;
   }

   public double getPrezzo() {
      return prezzo;
   }

   public PacchettoBuilder setPrezzo(double prezzo) {
      this.prezzo = prezzo;
      return this;
   }

   public int getQuantita() {
      return quantita;
   }

   public PacchettoBuilder setQuantita(int quantita) {
      this.quantita = quantita;
      return this;
   }

   public Venditore getVenditore() {
      return venditore;
   }

   public PacchettoBuilder setVenditore(Venditore venditore) {
      this.venditore = venditore;
      return this;
   }

   public Date getData() {
      return data;
   }

   public PacchettoBuilder setData(Date data) {  
      this.data = data;
      return this;
   }

   public Set<Prodotto> getListaProdotti() {
      return listaProdotti;
   }

   public PacchettoBuilder setListaProdotti(Set<Prodotto> listaProdotti) {
      this.listaProdotti = listaProdotti;
      return this;
   }

}
