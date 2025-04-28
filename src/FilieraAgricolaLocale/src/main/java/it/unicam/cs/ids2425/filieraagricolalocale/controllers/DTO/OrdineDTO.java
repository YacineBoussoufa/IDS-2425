package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;

public class OrdineDTO {

   private Date d;
   private String u;
   private Indirizzo i;
   private Pagamento m;

   public OrdineDTO(Date d, String u, Indirizzo i, Pagamento m) {
      this.d = d;
      this.u = u;
      this.i = i;
      this.m = m;
   }
   public Date getD() {
      return d;
   }
   public void setD(Date d) {
      this.d = d;
   }
   public String getU() {
      return u;
   }
   public void setU(String u) {
      this.u = u;
   }
   public Indirizzo getI() {
      return i;
   }
   public void setI(Indirizzo i) {
      this.i = i;
   }
   public Pagamento getM() {
      return m;
   }
   public void setM(Pagamento m) {
      this.m = m;
   }
   
}
