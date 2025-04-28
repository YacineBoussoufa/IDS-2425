package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;
import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;

public class UtenteDTO {
   
   public UtenteDTO(String nome, String cognome, Date dataDiNascita, String username, String password,
         List<Ruolo> listaRuoli) {
      Nome = nome;
      Cognome = cognome;
      this.dataDiNascita = dataDiNascita;
      this.username = username;
      this.password = password;
      this.listaRuoli = listaRuoli;
   }
   private String Nome;
   private String Cognome;
   private Date dataDiNascita;
   private String username;
   private String password;
   private List<Ruolo> listaRuoli;
   public String getNome() {
      return Nome;
   }
   public void setNome(String nome) {
      Nome = nome;
   }
   public String getCognome() {
      return Cognome;
   }
   public void setCognome(String cognome) {
      Cognome = cognome;
   }
   public Date getDataDiNascita() {
      return dataDiNascita;
   }
   public void setDataDiNascita(Date dataDiNascita) {
      this.dataDiNascita = dataDiNascita;
   }
   public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public List<Ruolo> getListaRuoli() {
      return listaRuoli;
   }
   public void setListaRuoli(List<Ruolo> listaRuoli) {
      this.listaRuoli = listaRuoli;
   }

}
