package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public non-sealed class Venditore implements Account {
	
	private String RagioneSociale;
	private String PIVA;
	@Id
	private String username;
	private String password;

	@ElementCollection(targetClass = RuoloVenditore.class) 
   @CollectionTable(name = "RUOLI_VENDITORE",
      joinColumns = @JoinColumn(name = "username"))
   @Column(name = "IdRuolo")
	private List<Ruolo> listaRuoli = new ArrayList<>();
	private String Descrizione;

	@ManyToOne(cascade = CascadeType.ALL)
	private POI Localizzazione;
	
   public Venditore(){}

	public Venditore(String RagioneSociale, String PIVA, String username, String password,
					 List<RuoloVenditore> listaRuoli, String Descrizione, POI Localizzazione) {
		this.RagioneSociale=RagioneSociale;
		this.PIVA=PIVA;
		this.username=username;
		this.password=password;

		if (listaRuoli != null) {
			this.listaRuoli.addAll(listaRuoli);
		}

		this.Descrizione=Descrizione;
		this.Localizzazione=Localizzazione;
		this.Localizzazione.setTipoPOI(TipoPOI.Azienda);
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

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username=username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public List<Ruolo> getListaRuoli() {
		return listaRuoli;
	}

	public void setListaRuoli(List<RuoloVenditore> listaRuoli) {
		if (listaRuoli != null) {
			this.listaRuoli.clear();
			this.listaRuoli.addAll(listaRuoli);
		}
	}

	public String getDescrizione() {
		return Descrizione;
	}
	
	public void setDescrizione(String Descrizione) {
		this.Descrizione=Descrizione;
	}
	
	public POI getLocalizzazione() {
		return Localizzazione;
	}
	
	public void setLocalizzazione(POI Localizzazione) {
		this.Localizzazione=Localizzazione;
	}

	@Override
	public boolean equals(Object o) {
	   if (o instanceof Venditore venditore) {
		   //username è la chiave primaria
		   return venditore.getUsername().equals(this.username);
	   } else {
		   return false;
	   }
	}
}