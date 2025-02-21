package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pagamento {
   
   @Id
   private int id;
   private String numeroCarta;
   private Date dataScadenza;
   private int cvv;
   
   public Pagamento(String numeroCarta, Date dataScadenza, int cvv) {
      this.numeroCarta = numeroCarta;
      this.dataScadenza = dataScadenza;
      this.cvv = cvv;
   }
   
   public Pagamento (){
      
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

}
