package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.text.DateFormat;
import java.util.Map;
import java.util.Collection;
import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Carrello;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Persona;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class UserService {
   
   public static Map<String, Persona> userRepository;

   public void creaUtente(String nome, String cognome, DateFormat dataDiNascita, String username, Ruolo ruolo, Carrello car){
      Persona p = new Persona(nome, cognome, dataDiNascita, username, ruolo);
      userRepository.put(username, p);
   }
   
   public void modificaUtente(String i, Persona p){
      userRepository.put(i, p);
   }

   public void rimuoviUtente(String i){
      userRepository.remove(i);
   }

   public Persona getUtente(String i){
      return userRepository.get(i);
   }

   public Collection<Persona> getElencoUtenti(){
      return userRepository.values();
   }

   public void assegnaRuolo(Ruolo r, String i){
      userRepository.get(i).setRuolo(r);
   }

}
