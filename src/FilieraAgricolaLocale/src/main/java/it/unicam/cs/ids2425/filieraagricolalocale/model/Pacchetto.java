package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@JsonDeserialize(builder = PacchettoBuilder.class)
public class Pacchetto extends Contenuto {

   @ManyToMany
   private Set<Prodotto> listaProdotti;

   private final String tipo = "Pacchetto";

   /**
    * Costruttore che genera i campi a partire da un builder.
    *
    * @param builder Builder per generare i campi.
    */
   public Pacchetto(PacchettoBuilder builder) {
      super(builder);

      this.listaProdotti = builder.getListaProdotti();
   }

   public Pacchetto() {

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
   public void approva() {
      for (Prodotto p : listaProdotti) {

         if (p.getStato() instanceof InConvalida)  {
            p.approva();
         } else if(p.getStato() instanceof Bozza) {
            throw new DatiIncorrettiException("Non si può approvare un pacchetto con prodotti bozza.");
         }

      }

      super.approva();
   }

   @Override
   public void pubblica() {
      for (Prodotto p : listaProdotti) {
         if (p.getStato() instanceof Bozza)  {
            p.pubblica();
         }
      }

      super.pubblica();
   }

   @Override
   public void setModifiche(Contenuto contenuto) {

      if (contenuto instanceof Pacchetto modifiche) {

         setNome(modifiche.getNome() == null ? getNome() : modifiche.getNome());
         setDescrizione(modifiche.getDescrizione() == null ? getDescrizione() : modifiche.getDescrizione());
         setPrezzo(modifiche.getPrezzo() == 0 ? getPrezzo() : modifiche.getPrezzo());
         setQuantita(modifiche.getQuantita() == 0 ? getQuantita() : modifiche.getQuantita());
         setData(modifiche.getData() == null ? getData() : modifiche.getData());
         setListaProdotti(modifiche.getListaProdotti() == null ? getListaProdotti() : modifiche.getListaProdotti());

         //Non è previsto che questi campi mutino
         setId(getId());
         setVenditore(getVenditore());

      }

   }

   @Override
   public String getTipo() {
      return tipo;
   }

}
