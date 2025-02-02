package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.text.DateFormat;
import java.util.Map;
import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Carrello;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Persona;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class UserService {
   
   public static Map<Integer, Persona> userRepository;

   public void creaUtente(String nome, String cognome, DateFormat dataDiNascita, String username, Ruolo ruolo,
         List<Indirizzo> indirizzi, Carrello car){
      Persona p = new Persona(nome, cognome, dataDiNascita, username, ruolo, indirizzi, car);
      userRepository.put(p.hashCode(), p);
   }
   
   public void modificaUtente(Integer i, Persona p){
      userRepository.put(i, p);
   }

   public void rimuoviUtente(Integer i){
      userRepository.remove(i);
   }

   public Persona getPersona(Integer i){
      return userRepository.get(i);
   }

   public void aggiungiIndirizzo(Integer i, Indirizzo ind){
      userRepository.get(i).aggiungiIndirizzo(ind);
   }

   public void modificaIndirizzo(Integer i, Indirizzo ind, int index){
      userRepository.get(i).getIndirizzi().remove(index);
      userRepository.get(i).aggiungiIndirizzo(ind);
   }

   public void rimuoviIndirizzo(Integer i, Indirizzo ind){
      userRepository.get(i).rimuoviIndirizzo(ind);
   }
}
