package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class Manifestazione extends EventoAbstract implements Evento {

	private Set<Venditore> aziendePartecipanti;
	private Set<Utente> personePartecipanti;

	public Manifestazione(EventoBuilder<?> builder, Set<Venditore> aziendePartecipanti, Set<Utente> personePartecipanti) {
		super(builder);
		this.aziendePartecipanti = aziendePartecipanti;
		this.personePartecipanti = personePartecipanti;
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public int getNumeroMaxPartecipanti() {
		return numeroMaxPartecipanti;
	}
	
	@Override
	public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
		this.numeroMaxPartecipanti=numeroMaxPartecipanti;
	}
	
	@Override
	public DateFormat getData() {
		return data;
	}

	@Override
	public void setData(DateFormat data) {
		this.data=data;
	}

	@Override
	public POI getPuntoDiInteresse() {
		return puntoDiInteresse;
	}

	@Override
	public void setPuntoDiInteresse(POI puntoDiInteresse) {
		this.puntoDiInteresse=puntoDiInteresse;
	}

	public Set<Venditore> getAziendePartecipanti() {
		return aziendePartecipanti;
	}
	public void setAziendePartecipanti(Set<Venditore> aziendePartecipanti) {
		this.aziendePartecipanti = aziendePartecipanti;
	}

	public Set<Utente> getPersonePartecipanti() {
		return personePartecipanti;
	}
	public void setPersonePartecipanti(Set<Utente> personePartecipanti) {
		this.personePartecipanti = personePartecipanti;
	}
	
	@Override
	public Utente getAnimatore() {
		 return this.animatore;
	}

}