package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
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
                          String password, List<Ruolo> ruoli){

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
					 List<Ruolo> listaRuoli, String Descrizione, POI Localizzazione){
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

   public Account getAccount(String i){
      Optional<Utente> user = userRepository.findById(i);
      if (user.isPresent()) {
         return user.get();
      }

      Optional<Venditore> seller = sellerRepository.findById(i);
      if (seller.isPresent()) {
         return seller.get();
      }

      return null;
   }

   public Iterable<Utente> getElencoUtenti(){
      return userRepository.findAll();
   }

   public Iterable<Venditore> getElencoVenditore(){
      return sellerRepository.findAll();
   }

   public void modificaRuoliUtente(List<Ruolo> r, String i){
      Utente s = userRepository.findById(i).get();
      s.setListaRuoli(r);
      userRepository.save(s);
   }

   public void modificaRuoliVenditore(List<Ruolo> r, String i){
      Venditore s = sellerRepository.findById(i).get();
      s.setListaRuoli(r);
      sellerRepository.save(s);
   }

   public Account getCurrentUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
         return null;
      }
      String username = authentication.getName();
      return getAccount(username);
   }

   
   /**
    * @param id utente
    * @return carrello utente
    */
   public Carrello getCarrelloUtente(String id){

      Utente u = userRepository.findById(id).orElseThrow(() -> new DatiIncorrettiException(id + " Non trovato"));

      return u.getCarrello();
   }

   /**
      * @param id utente, contenuto, quantita
      * @return 
      */
   public void aggiungiContenutoCarrello(String id, Contenuto p, int quantita){

      Utente u = userRepository.findById(id).orElseThrow(() -> new DatiIncorrettiException(id + " Non trovato"));

      u.getCarrello().aggiungiContenuto(p, quantita);
      userRepository.save(u);
   }
   
   /**
      * @param id utente, contenuto
      * @return 
      */
   public void rimuoviContenutoCarrello(String id, Contenuto p){

      Utente u = userRepository.findById(id).orElseThrow(() -> new DatiIncorrettiException(id + " Non trovato"));

      u.getCarrello().rimuoviContenuto(p);
      userRepository.save(u);
   }

   /**
      * @param id utente, contenuto, quantita
      * @return 
      */
   public void modificaQuantitaCarrello(String id, Contenuto p, int quantita){

      Utente u = userRepository.findById(id).orElseThrow(() -> new DatiIncorrettiException(id + " Non trovato"));

      u.getCarrello().modificaQuantita(p, quantita);
      userRepository.save(u);
   }

   /**
      * @param id utente
      * @return 
      */
   public void svuotaCarrello(String id){

      Utente u = userRepository.findById(id).orElseThrow(() -> new DatiIncorrettiException(id + " Non trovato"));

      u.getCarrello().svuota();
      userRepository.save(u);
   }

}
