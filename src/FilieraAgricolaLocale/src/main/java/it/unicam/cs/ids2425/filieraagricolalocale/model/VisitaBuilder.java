package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VisitaBuilder implements EventoBuilder{

    private Date data;
    private String nome;
    private String descrizione;
    private int numeroMaxPartecipanti;
    private POI puntoDiInteresse;
    private Utente animatore;

    private Set<Utente> personePartecipanti;
    private Proposta proposta;

    public Visita build() {
        return new Visita(this);
    }

    public String getNome() {
        return nome;
    }

    public VisitaBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public VisitaBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public VisitaBuilder setNumeroMaxPartecipanti(int numero) {
        this.numeroMaxPartecipanti = numero;
        return this;
    }

    public Date getData() {
        return data;
    }

    public VisitaBuilder setData(Date data) {
        this.data = data;
        return this;
    }

    public POI getPuntoDiInteresse() {
        return puntoDiInteresse;
    }

    public VisitaBuilder setPuntoDiInteresse(POI puntoDiInteresse) {
        this.puntoDiInteresse = puntoDiInteresse;
        return this;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public VisitaBuilder setAnimatore(Utente animatore) {
        this.animatore = animatore;
        return this;
    }

    public Set<Utente> getPersonePartecipanti() {
        return personePartecipanti;
    }

    public VisitaBuilder setPersonePartecipanti(Set<Utente> personePartecipanti) {
        if (this.personePartecipanti == null) {
            this.personePartecipanti = new HashSet<>(); // Initialize if null
        }

        this.personePartecipanti.clear();
        this.personePartecipanti.addAll(personePartecipanti);
        return this;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public VisitaBuilder setProposta(Proposta proposta) {
        this.proposta = proposta;
        return this;
    }

}