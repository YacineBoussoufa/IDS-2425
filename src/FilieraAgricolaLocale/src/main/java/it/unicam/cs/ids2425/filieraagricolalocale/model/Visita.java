package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@JsonDeserialize(builder = VisitaBuilder.class)
@Entity
public class Visita extends EventoAbstract {

    @ManyToMany
    private Set<Utente> personePartecipanti;

    @JsonManagedReference
    @OneToOne
    private final Proposta proposta;

    public Visita(VisitaBuilder builder) {
        super(builder);

        this.personePartecipanti = builder.getPersonePartecipanti();
        this.proposta = builder.getProposta();
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Set<Utente> getPersonePartecipanti() {
        return personePartecipanti;
    }

    public void setPersonePartecipanti(Set<Utente> personePartecipanti) {
        this.personePartecipanti = personePartecipanti;
    }

}