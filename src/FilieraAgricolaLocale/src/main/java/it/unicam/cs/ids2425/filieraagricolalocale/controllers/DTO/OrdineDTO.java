package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;
import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class OrdineDTO {

   private Date d;
   private List<ElementoOrdineDTO> linee;
   private Utente u;
   private Indirizzo i;
   private Pagamento m;

   public OrdineDTO(Date d, List<ElementoOrdineDTO> linee, Utente u, Indirizzo i, Pagamento m) {
      this.d = d;
      this.linee = linee;
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
   public List<ElementoOrdineDTO> getLinee() {
      return linee;
   }
   public void setLinee(List<ElementoOrdineDTO> linee) {
      this.linee = linee;
   }
   public Utente getU() {
      return u;
   }
   public void setU(Utente u) {
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
