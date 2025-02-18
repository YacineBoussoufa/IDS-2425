package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class Visita extends EventoAbstract {

    private Set<Utente> personePartecipanti;
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