package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@JsonDeserialize(builder = VisitaBuilder.class)
@Entity
public class Visita extends EventoAbstract {

    @ManyToMany
    private Set<Utente> personePartecipanti;

    @JsonManagedReference
    @OneToOne(mappedBy = "visita", cascade = CascadeType.ALL)
    private final Proposta proposta;

    public Visita(VisitaBuilder builder) {
        super(builder);

        this.personePartecipanti = builder.getPersonePartecipanti();
        this.proposta = new Proposta(getAnimatore(), this, builder.getPropostaVenditore());
    }

    public Visita() {
        this.proposta = null;
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