package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;

@Component("OrdineService")
public class OrdineService {
   
   public static Map<Integer, Ordine> ordineRepository = new HashMap<>();
   private MiddlewareOrdine middlewareHead;

   public void setMiddleware(MiddlewareOrdine m){
      this.middlewareHead = m;
   }

   public void creaOrdine(Date dataCreazione, Map<Contenuto, Integer> mappaProdotti, Utente u, Indirizzo i, Pagamento m){
  
      if(!middlewareHead.check(dataCreazione, mappaProdotti, u, i, m)){
         throw new DatiIncorrettiException("Errore nella creazione ordine");
      }
      Ordine o = new Ordine(dataCreazione, mappaProdotti, u, i, m);
      ordineRepository.put(1, o);
   }

   public void modificaIndirizzo(Integer i, Indirizzo o){
      ordineRepository.get(i).setIndirizzo(o);
   }

   public void modificaDataDiConsegna(Integer i, Date o){
      ordineRepository.get(i).setDataDiConsegna(o);
   }

   public void rimuoviOrdine(Integer i){
      ordineRepository.remove(i);
   }

   public Ordine getOrdine(Integer i){
      return ordineRepository.get(i);
   }

   public Collection<Ordine> getOrdiniUtente(String i){
      return ordineRepository.values().stream().filter(o -> o.getUser().getUsername().equals(i)).toList();
   }   

   public Collection<Ordine> getOrdiniVenditore(String i){
      return ordineRepository.values().stream().filter(o -> o.getArticoli().stream().anyMatch(p -> p.getProdotto().getVenditore().getUsername().equals(i))).toList();
   }   
}
