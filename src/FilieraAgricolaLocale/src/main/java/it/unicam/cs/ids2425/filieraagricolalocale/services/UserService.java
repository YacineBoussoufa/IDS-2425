package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloUtente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloVenditore;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente.MiddlewareUtente;

public class UserService {
   
   public static Map<String, Utente> userRepository = new HashMap<>();
   public static Map<String, Venditore> sellerRepository = new HashMap<>(); 
   private MiddlewareUtente middlewareHead;

   public void setMiddleware(MiddlewareUtente m){
      this.middlewareHead = m;
   }

   public void creaUtente(String nome, String cognome, Date dataDiNascita, String username,
                          String password, List<RuoloUtente> ruoli){

      Utente p = new Utente(nome, cognome, dataDiNascita, username, password, ruoli);
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      userRepository.put(username, p);
   }

   public void creaUtente(Utente p){
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      userRepository.put(p.getUsername(), p);
   }

   public void creaVenditore(Venditore p){
      if(!middlewareHead.check(p)){
      throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      sellerRepository.put(p.getUsername(), p);
   }
   
   public void creaVenditore(String RagioneSociale, String PIVA, String username, String password,
					 List<RuoloVenditore> listaRuoli, String Descrizione, POI Localizzazione){
      Venditore p = new Venditore(RagioneSociale, PIVA, username, password, listaRuoli, Descrizione, Localizzazione);
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      sellerRepository.put(username, p);
   }

   public void modificaUtente(String i, Utente p){
      if(userRepository.get(i) == null) throw new DatiIncorrettiException();
      userRepository.put(i, p);
   }

   public void modificaVenditore(String i, Venditore p){
      if(userRepository.get(i) == null) throw new DatiIncorrettiException();
      sellerRepository.put(i, p);
   }

   public void rimuoviUtente(String i){
      if(userRepository.get(i) == null) throw new DatiIncorrettiException();
      userRepository.remove(i);
   }

   public void rimuoviVenditore(String i){
      if(userRepository.get(i) == null) throw new DatiIncorrettiException();
      sellerRepository.remove(i);
   }

   public Utente getUtente(String i){
      return userRepository.get(i);
   }

   public Venditore getVenditore(String i){
      return sellerRepository.get(i);
   }

   public Collection<Utente> getElencoUtenti(){
      return userRepository.values();
   }

   public Collection<Venditore> getElencoVenditore(){
      return sellerRepository.values();
   }

   public void modificaRuoliUtente(List<RuoloUtente> r, String i){
      userRepository.get(i).setListaRuoli(r);
   }

   public void modificaRuoliVenditore(List<RuoloVenditore> r, String i){
      sellerRepository.get(i).setListaRuoli(r);
      
   }

}
