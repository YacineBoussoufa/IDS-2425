package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

public class Persona implements Utente {

   private final int id;
   private String nome;
   private String cognome;
   private Date dataDiNascita;
   private String username;
   private Ruolo ruolo;
   private final Carrello carrello;
   private String numeroCarta;
   private Date dataScadenza;
   private int cvv;


   /**
    * @param nome
    * @param cognome
    * @param dataDiNascita
    * @param username
    * @param ruolo
    */
   public Persona(String nome, String cognome, Date dataDiNascita, String username, Ruolo ruolo) {
      this.nome = nome;
      this.cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.ruolo = ruolo;
      this.carrello = new Carrello(this);
      this.id = 0;
   }

   /**
    * @param nome
    * @param cognome
    * @param dataDiNascita
    * @param username
    * @param ruolo
    * @param carrello
    * @param numeroCarta
    * @param dataScadenza
    * @param cvv
    */
   public Persona(String nome, String cognome, Date dataDiNascita, String username, Ruolo ruolo, Carrello carrello,
   String numeroCarta, Date dataScadenza, int cvv) {
      this.nome = nome;
      this.cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.ruolo = ruolo;
      this.carrello = carrello;
      this.numeroCarta = numeroCarta;
      this.dataScadenza = dataScadenza;
      this.cvv = cvv;
      this.id = 0;
   }

   public int getId() {
      return id;
   }

   public String getNumeroCarta() {
      return numeroCarta;
   }

   public void setNumeroCarta(String numeroCarta) {
      this.numeroCarta = numeroCarta;
   }

   public Date getDataScadenza() {
      return dataScadenza;
   }

   public void setDataScadenza(Date dataScadenza) {
      this.dataScadenza = dataScadenza;
   }

   public int getCvv() {
      return cvv;
   }

   public void setCvv(int cvv) {
      this.cvv = cvv;
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

   public Date getDataDiNascita() {
      return dataDiNascita;
   }

   public void setDataDiNascita(Date dataDiNascita) {
      this.dataDiNascita = dataDiNascita;
   }

   @Override
   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
   
   public Carrello getCarrello() {
      return carrello;
   }

}
