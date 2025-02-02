package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.List;

public class Persona implements Utente {

   private String nome;
   private String cognome;
   private DateFormat dataDiNascita;
   private String username;
   private Ruolo ruolo;
   private List<Indirizzo> indirizzi; 
   private final Carrello carrello;

   /**
    * @param nome
    * @param cognome
    * @param dataDiNascita
    * @param username
    * @param ruolo
    * @param indirizzi
    */
   public Persona(String nome, String cognome, DateFormat dataDiNascita, String username, Ruolo ruolo,
         List<Indirizzo> indirizzi, Carrello car) {
      this.nome = nome;
      this.cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.ruolo = ruolo;
      this.indirizzi = indirizzi;
      this.carrello = car;
   }

   /**
    * @param nome
    * @param cognome
    * @param dataDiNascita
    * @param username
    * @param ruolo
    */
   public Persona(String nome, String cognome, DateFormat dataDiNascita, String username, Ruolo ruolo, Carrello car) {
      this.nome = nome;
      this.cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.ruolo = ruolo;
      this.carrello = car;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCognome() {
      return cognome;
   }

   public void setCognome(String cognome) {
      this.cognome = cognome;
   }

   @Override
   public Ruolo getRuolo() {
      return this.ruolo;
   }

   public void setRuolo(Ruolo ruolo) {
      this.ruolo = ruolo;
   }

   public DateFormat getDataDiNascita() {
      return dataDiNascita;
   }

   public void setDataDiNascita(DateFormat dataDiNascita) {
      this.dataDiNascita = dataDiNascita;
   }

   @Override
   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public List<Indirizzo> getIndirizzi() {
      return indirizzi;
   }

   public void setIndirizzi(List<Indirizzo> indirizzi) {
      this.indirizzi = indirizzi;
   }
   
   public Carrello getCarrello() {
      return carrello;
   }

   public void aggiungiIndirizzo(Indirizzo i){
      this.indirizzi.add(i);
   }
   
   public void rimuoviIndirizzo(Indirizzo i){
      this.indirizzi.remove(i);
   }

}
