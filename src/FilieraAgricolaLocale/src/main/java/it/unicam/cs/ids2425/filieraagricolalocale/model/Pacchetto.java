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

   @Override
   public Pacchetto setModifiche(Contenuto contenuto) {

      if (contenuto instanceof Pacchetto modifiche) {
         PacchettoBuilder builder = PacchettoBuilder.copiaDa(modifiche);

         builder.setNome(modifiche.getNome() == null ? getNome() : modifiche.getNome());
         builder.setDescrizione(modifiche.getDescrizione() == null ? getDescrizione() : modifiche.getDescrizione());
         builder.setPrezzo(modifiche.getPrezzo() == 0 ? getPrezzo() : modifiche.getPrezzo());
         builder.setQuantita(modifiche.getQuantita() == 0 ? getQuantita() : modifiche.getQuantita());
         builder.setData(modifiche.getData() == null ? getData() : modifiche.getData());
         builder.setListaProdotti(modifiche.getListaProdotti() == null ? getListaProdotti() : modifiche.getListaProdotti());

         //Non è previsto che questi campi mutino
         builder.setId(getId());
         builder.setVenditore(getVenditore());

         return builder.build();
      }

      return null;

   }


}
