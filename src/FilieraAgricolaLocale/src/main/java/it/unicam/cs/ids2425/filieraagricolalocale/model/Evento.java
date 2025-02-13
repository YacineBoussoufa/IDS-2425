package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;

public interface Evento {
	
	public String getNome();
	
	public void setNome(String Nome);
	
	public String getDescrizione();
	
	public void setDescrizione(String Descrizione);
	
	public int getNumeroMaxPartecipanti();
	
	public void setNumeroMaxPartecipanti(int NumeroMaxPartecipanti);
	
	public DateFormat getData();
	
	public void setData(DateFormat Data);

	public POI getPuntoDiInteresse();

	public void setPuntoDiInteresse(POI PuntoDiInteresse);

	public Persona getAnimatore();
	
}