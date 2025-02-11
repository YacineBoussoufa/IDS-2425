package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Venditore {
	
	private String RagioneSociale;
	private String PIVA;
	private String Descrizione;
	private String Localizzazione;
	
	public Venditore(String RagioneSociale, String PIVA, String Descrizione, String Localizzazione) {
		this.RagioneSociale=RagioneSociale;
		this.PIVA=PIVA;
		this.Descrizione=Descrizione;
		this.Localizzazione=Localizzazione;
	}
	
	public String getRagioneSociale() {
		return RagioneSociale;
	}
	
	public void setRagioneSociale(String RagioneSociale) {
		this.RagioneSociale=RagioneSociale;
	}
	
	public String getPIVA() {
		return PIVA;
	}
	
	public void setPIVA(String PIVA) {
		this.PIVA=PIVA;
	}
	
	public String getDescrizione() {
		return Descrizione;
	}
	
	public void setDescrizione(String Descrizione) {
		this.Descrizione=Descrizione;
	}
	
	public String getLocalizzazione() {
		return Localizzazione;
	}
	
	public void setLocalizzazione(String Localizzazione) {
		this.Localizzazione=Localizzazione;
	}
}