package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.text.DateFormat;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;

public class OrdineService {
   
   public static Map<Integer, Ordine> ordineRepository;
   private MiddlewareOrdine middlewareHead;

   public OrdineService(MiddlewareOrdine middlewares){
      this.middlewareHead = middlewares;
   }

   public void creaOrdine(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i){
      if(!middlewareHead.check(dataCreazione, mappaProdotti, u, i)){
         throw new IllegalArgumentException("Errore nella creazione ordine");
      }
      Ordine o = new Ordine(dataCreazione, mappaProdotti, u, i);
      ordineRepository.put(o.hashCode(), o);
   }

   public void modificaIndirizzo(Integer i, Indirizzo o){
      ordineRepository.get(i).setIndirizzo(o);
   }

   public void setEvaso(Integer i, boolean o){
      ordineRepository.get(i).setEvaso(o);;
   }

   public void rimuoviOrdine(Integer i){
      ordineRepository.remove(i);
   }

   public void getOrdine(Integer i){
      ordineRepository.get(i);
   }   
}
