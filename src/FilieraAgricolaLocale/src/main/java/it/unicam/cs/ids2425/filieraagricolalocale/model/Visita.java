package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = VisitaBuilder.class)
public class Visita extends EventoAbstract {

    private Set<Utente> personePartecipanti;
    @JsonManagedReference
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