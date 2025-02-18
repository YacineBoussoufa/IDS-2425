package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class Manifestazione extends EventoAbstract {

	private Set<Venditore> aziendePartecipanti;
	private Set<Utente> personePartecipanti;

	public Manifestazione(ManifestazioneBuilder builder) {
		super(builder);

		this.aziendePartecipanti = builder.getAziendePartecipanti();
		this.personePartecipanti = builder.getPersonePartecipanti();
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

}