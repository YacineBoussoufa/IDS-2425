package it.unicam.cs.ids2425.filieraagricolalocale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Indirizzo {

   @Id
   private int id;
   private String via;
   private int civico;
   private String provincia;
   private String comune;
   private String regione;
   private String CAP;
   private String dettagli;
   
   public Indirizzo(String via, int civico, String provincia, String comune, String regione, String cAP,
         String dettagli) {
      this.via = via;
      this.civico = civico;
      this.provincia = provincia;
      this.comune = comune;
      this.regione = regione;
      CAP = cAP;
      this.dettagli = dettagli;
   }

   public Indirizzo (){
      
   }

   public int getId() {
      return id;
   }

   public String getVia() {
      return via;
   }

   public void setVia(String via) {
      this.via = via;
   }

   public int getCivico() {
      return civico;
   }

   public void setCivico(int civico) {
      this.civico = civico;
   }

   public String getProvincia() {
      return provincia;
   }
   
   public void setProvincia(String provincia) {
      this.provincia = provincia;
   }

   public String getComune() {
      return comune;
   }
   
   public void setComune(String comune) {
      this.comune = comune;
   }

   public String getRegione() {
      return regione;
   }

   public void setRegione(String regione) {
      this.regione = regione;
   }

   public String getCAP() {
      return CAP;
   }

   public void setCAP(String cAP) {
      CAP = cAP;
   }

   public String getDettagli() {
      return dettagli;
   }
   
   public void setDettagli(String dettagli) {
      this.dettagli = dettagli;
   }

}
