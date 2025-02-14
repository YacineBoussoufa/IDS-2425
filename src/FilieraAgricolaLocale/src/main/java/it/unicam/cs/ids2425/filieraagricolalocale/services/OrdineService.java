package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.Date;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;

public class OrdineService {
   
   public static Map<Integer, Ordine> ordineRepository;
   private MiddlewareOrdine middlewareHead;

   public OrdineService(MiddlewareOrdine middlewares){
      this.middlewareHead = middlewares;
   }

   public void creaOrdine(Date dataCreazione, Map<Prodotto, Integer> mappaProdotti, Account u, Indirizzo i, Pagamento m){
      if(!middlewareHead.check(dataCreazione, mappaProdotti, u, i, m)){
         throw new DatiIncorrettiException("Errore nella creazione ordine");
      }
      Ordine o = new Ordine(dataCreazione, mappaProdotti, u, i, m);
      ordineRepository.put(o.hashCode(), o);
   }

   public void modificaIndirizzo(Integer i, Indirizzo o){
      ordineRepository.get(i).setIndirizzo(o);
   }

   public void modificaDataDiConsegna(Integer i, Date o){
      ordineRepository.get(i).setDataDiConsegna(o);
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
