package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;

public class Manifestazione implements Evento {
	
	private DateFormat Data;
	private String Nome;
	private String Descrizione;
	private int NumeroMaxPartecipanti;
	
	public Manifestazione(DateFormat Data, String Nome, String Descrizione, int NumeroMaxPartecipanti) {
		this.Data=Data;
		this.Nome=Nome;
		this.Descrizione=Descrizione;
		this.NumeroMaxPartecipanti=NumeroMaxPartecipanti;
	}
	
	@Override
	public String getNome() {
		return Nome;
	}
	
	@Override
	public void setNome(String Nome) {
		this.Nome=Nome;
	}
	
	@Override
	public String getDescrizione() {
		return Descrizione;
	}
	
	@Override
	public void setDescrizione(String Descrizione) {
		this.Descrizione = Descrizione;
	}
	
	@Override
	public int getNumeroMaxPartecipanti() {
		return NumeroMaxPartecipanti;
	}
	
	@Override
	public void setNumeroMaxPartecipanti(int NumeroMaxPartecipanti) {
		this.NumeroMaxPartecipanti=NumeroMaxPartecipanti;
	}
	
	@Override
	public DateFormat getData() {
		return Data;
	}

	@Override
	public void setData(DateFormat Data) {
		this.Data=Data;
	}
	
}