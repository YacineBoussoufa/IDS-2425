package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.OrdineRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;

@Service
public class OrdineService {
   
   //public static Map<Integer, Ordine> ordineRepository = new HashMap<>();

   private OrdineRepository ordineRepository;
   private MiddlewareOrdine middlewareHead;

   @Autowired
   public OrdineService(OrdineRepository o){
      this.ordineRepository = o;
   }

   public void setMiddleware(MiddlewareOrdine m){
      this.middlewareHead = m;
   }

   public void creaOrdine(Date dataCreazione, Utente u, Indirizzo i, Pagamento m){

      Ordine o = new Ordine(dataCreazione, u.getCarrello(), u, i, m);
      if(!middlewareHead.check(o)){
         throw new DatiIncorrettiException("Errore nella creazione ordine");
      }
      
      ordineRepository.save(o);
   }

   public void modificaIndirizzo(Integer i, Indirizzo o){
      Ordine ord = ordineRepository.findById(i).get();
      ord.setIndirizzo(o);
      ordineRepository.save(ord);
   }

   public void modificaDataDiConsegna(Integer i, Date o){
      Ordine ord = ordineRepository.findById(i).get();
      ord.setDataDiConsegna(o);
      ordineRepository.save(ord);
   }

   public void rimuoviOrdine(Integer i){
      ordineRepository.deleteById(i);
   }

   public Ordine getOrdine(Integer i){
      return ordineRepository.findById(i).orElseGet(() -> null);
   }

   public Collection<Ordine> getOrdiniUtente(String i){
      Collection<Ordine> s = new LinkedList<>();
      ordineRepository.findAll().forEach(o -> {if(o.getUser().getUsername().equals(i)) s.add(o);});
      return s;
   }   

   public Collection<Ordine> getOrdiniVenditore(String i){
      Collection<Ordine> s = new LinkedList<>();
      ordineRepository.findAll().forEach(o -> {if(o.getArticoli().stream().anyMatch(p -> p.getProdotto().getVenditore().getUsername().equals(i))) s.add(o);});
      return s;
   }   
}
