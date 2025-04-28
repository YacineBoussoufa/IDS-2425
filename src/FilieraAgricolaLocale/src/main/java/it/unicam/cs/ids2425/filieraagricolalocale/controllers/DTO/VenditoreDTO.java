package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;

public class VenditoreDTO {
   
   private String RagioneSociale;
   private String PIVA;
   private String username;
   private String password;
   private List<Ruolo> listaRuoli;
   private String descrizione;
   private POI Localizzazione;
   public VenditoreDTO(String ragioneSociale, String pIVA, String username, String password, List<Ruolo> listaRuoli,
         String descrizione, POI localizzazione) {
      RagioneSociale = ragioneSociale;
      PIVA = pIVA;
      this.username = username;
      this.password = password;
      this.listaRuoli = listaRuoli;
      this.descrizione = descrizione;
      Localizzazione = localizzazione;
   }
   public String getRagioneSociale() {
      return RagioneSociale;
   }
   public void setRagioneSociale(String ragioneSociale) {
      RagioneSociale = ragioneSociale;
   }
   public String getPIVA() {
      return PIVA;
   }
   public void setPIVA(String pIVA) {
      PIVA = pIVA;
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
   public String getDescrizione() {
      return descrizione;
   }
   public void setDescrizione(String descrizione) {
      this.descrizione = descrizione;
   }
   public POI getLocalizzazione() {
      return Localizzazione;
   }
   public void setLocalizzazione(POI localizzazione) {
      Localizzazione = localizzazione;
   }

}
