package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public non-sealed class Utente implements Account {

   private final int id;
   private String nome;
   private String cognome;
   private Date dataDiNascita;
   private String username;
   private String password;
   private List<Ruolo> listaRuoli = new ArrayList<>();
   private final Carrello carrello;
   //TODO spostare questi in ordine
   private String numeroCarta;
   private Date dataScadenza;
   private int cvv;


   /**
    * @param nome
    * @param cognome
    * @param dataDiNascita
    * @param username
    * @param listaRuoli
    */
   public Utente(String nome, String cognome, Date dataDiNascita, String username, String password, List<RuoloUtente> listaRuoli) {
      this.nome = nome;
      this.cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.password = password;
      this.listaRuoli.addAll(listaRuoli);
      this.carrello = new Carrello(this);
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
   public List<Ruolo> getListaRuoli() {
      return listaRuoli;
   }

   public void setRuolo(List<RuoloUtente> listaRuoli) {
      this.listaRuoli.clear();
      this.listaRuoli.addAll(listaRuoli);
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

   @Override
   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   
   public Carrello getCarrello() {
      return carrello;
   }

}
