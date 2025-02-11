package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class Carrello {

   private final Utente id;
   private Map<Prodotto, Integer> listaProdotti;
   private DateFormat ultimaModifica;

   public Carrello(Utente id, Map<Prodotto, Integer> listaProdotti) {
      this.id = id;
      this.listaProdotti = listaProdotti;
      this.ultimaModifica = DateFormat.getDateInstance();
   }

   public Carrello(Utente id) {
      this.id = id;
      this.listaProdotti = new HashMap<>();
      this.ultimaModifica = DateFormat.getDateInstance();
   }

   public Map<Prodotto, Integer> getListaProdotti() {
      return listaProdotti;
   }
   
   public void aggiungiProdotto(Prodotto p, Integer q){
      this.listaProdotti.put(p, q);
      this.ultimaModifica = DateFormat.getDateInstance();
   }

   public void rimuoviProdotto(Prodotto p){
      this.listaProdotti.remove(p);
      this.ultimaModifica = DateFormat.getDateInstance();
   }

   public void rimuoviTutto(){
      this.listaProdotti.clear();
      this.ultimaModifica = DateFormat.getDateInstance();
   }

   public DateFormat getUltimaModifica() {
      return ultimaModifica;
   }
   
   public Utente getId() {
      return id;
   }

}
