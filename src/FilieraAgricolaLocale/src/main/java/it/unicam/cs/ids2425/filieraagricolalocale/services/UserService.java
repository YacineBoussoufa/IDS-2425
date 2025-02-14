package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Date;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Carrello;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloUtente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;

public class UserService {
   
   public static Map<String, Utente> userRepository;

   public void creaUtente(String nome, String cognome, Date dataDiNascita, String username,
                          String password, List<RuoloUtente> ruoli){
      //TODO ESEGUIRE CONTROLLI CON MIDDLEWARE
      Utente p = new Utente(nome, cognome, dataDiNascita, username, password, ruoli);
      userRepository.put(username, p);
   }
   
   public void modificaUtente(String i, Utente p){
      userRepository.put(i, p);
   }

   public void rimuoviUtente(String i){
      userRepository.remove(i);
   }

   public Utente getUtente(String i){
      return userRepository.get(i);
   }

   public Collection<Utente> getElencoUtenti(){
      return userRepository.values();
   }

   public void assegnaRuolo(Ruolo r, String i){
      //todo
   }

}
