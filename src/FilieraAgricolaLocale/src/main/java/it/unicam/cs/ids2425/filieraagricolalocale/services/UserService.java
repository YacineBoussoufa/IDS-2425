package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloUtente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloVenditore;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.UtenteRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VenditoreRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente.MiddlewareUtente;

@Service
public class UserService {
   
   //public static Map<String, Utente> userRepository = new HashMap<>();
   //public static Map<String, Venditore> sellerRepository = new HashMap<>(); 

   private UtenteRepository userRepository;
   private VenditoreRepository sellerRepository;
   private MiddlewareUtente middlewareHead;

   @Autowired
   public UserService(UtenteRepository u, VenditoreRepository v){
      this.userRepository = u;
      this.sellerRepository = v;
   }

   public void setMiddleware(MiddlewareUtente m){
      this.middlewareHead = m;
   }

   public void creaUtente(String nome, String cognome, Date dataDiNascita, String username,
                          String password, List<RuoloUtente> ruoli){

      Utente p = new Utente(nome, cognome, dataDiNascita, username, password, ruoli);
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      userRepository.save(p);
   }

   public void creaUtente(Utente p){
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      userRepository.save(p);
   }

   public void creaVenditore(Venditore p){
      if(!middlewareHead.check(p)){
      throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      sellerRepository.save(p);
   }
   
   public void creaVenditore(String RagioneSociale, String PIVA, String username, String password,
					 List<RuoloVenditore> listaRuoli, String Descrizione, POI Localizzazione){
      Venditore p = new Venditore(RagioneSociale, PIVA, username, password, listaRuoli, Descrizione, Localizzazione);
      if(!middlewareHead.check(p)){
         throw new DatiIncorrettiException("Errore nella creazione utente");
      }
      sellerRepository.save(p);
   }

   public void modificaUtente(String i, Utente p){
      //TODO consider this
      if(userRepository.findById(i).isEmpty()) throw new DatiIncorrettiException();
      userRepository.save(p);
   }

   public void modificaVenditore(String i, Venditore p){
      if(sellerRepository.findById(i).isEmpty()) throw new DatiIncorrettiException();
      sellerRepository.save(p);
   }

   public void rimuoviUtente(String i){
      if(userRepository.findById(i).isEmpty()) throw new DatiIncorrettiException();
      userRepository.deleteById(i);
   }

   public void rimuoviVenditore(String i){
      if(sellerRepository.findById(i).isEmpty()) throw new DatiIncorrettiException();
      sellerRepository.deleteById(i);
   }

   public Utente getUtente(String i){
      return userRepository.findById(i).orElseGet(() -> null);
   }

   public Venditore getVenditore(String i){
      return sellerRepository.findById(i).orElseGet(() -> null);
   }

   public Iterable<Utente> getElencoUtenti(){
      return userRepository.findAll();
   }

   public Iterable<Venditore> getElencoVenditore(){
      return sellerRepository.findAll();
   }

   public void modificaRuoliUtente(List<RuoloUtente> r, String i){
      Utente s = userRepository.findById(i).get();
      s.setListaRuoli(r);
      userRepository.save(s);
   }

   public void modificaRuoliVenditore(List<RuoloVenditore> r, String i){
      Venditore s = sellerRepository.findById(i).get();
      s.setListaRuoli(r);
      sellerRepository.save(s);
   }

}
