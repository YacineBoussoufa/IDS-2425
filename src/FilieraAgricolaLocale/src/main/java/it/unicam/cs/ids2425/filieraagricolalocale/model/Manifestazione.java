package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@JsonDeserialize(builder = ManifestazioneBuilder.class)
@Entity
public class Manifestazione extends EventoAbstract {

	@ManyToMany
	private Set<Venditore> aziendePartecipanti;
	@ManyToMany
	private Set<Utente> personePartecipanti;

	public Manifestazione(ManifestazioneBuilder builder) {
		super(builder);

		this.aziendePartecipanti = builder.getAziendePartecipanti();
		this.personePartecipanti = builder.getPersonePartecipanti();
	}

	public Manifestazione() {

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