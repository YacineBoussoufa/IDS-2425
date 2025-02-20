package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@JsonDeserialize(builder = PacchettoBuilder.class)
@DiscriminatorValue("PACCHETTO")
public class Pacchetto extends Contenuto {

   @ManyToMany
   private Set<Prodotto> listaProdotti;
   

   // public Pacchetto(String nome, String descrizione, double prezzo,
   //                  Set<Prodotto> listaProdotti, Date data, Venditore v) {
   //    this.nome = nome;
   //    this.descrizione = descrizione;
   //    this.prezzo = prezzo;
   //    this.listaProdotti = listaProdotti;
   //    this.data = data;
   //    this.statoApprovazione = new Bozza(this);
   //    this.venditore = v;
   //    this.approvato = false;
   //    this.id = 0;
   // }

   /**
    * Costruttore che genera i campi a partire da un builder.
    *
    * @param builder Builder per generare i campi.
    */
   public Pacchetto(PacchettoBuilder builder) {
      super(builder);

      this.listaProdotti = builder.getListaProdotti();
   }

   public void setListaProdotti(Set<Prodotto> listaProdotti) {
      this.listaProdotti = listaProdotti;
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


}
