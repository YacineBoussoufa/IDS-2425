package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class EventoBuilder<T extends EventoAbstract> {

    protected DateFormat data;
    protected String nome;
    protected String descrizione;
    protected int numeroMaxPartecipanti;
    protected POI puntoDiInteresse;
    protected Utente animatore;

    public String getNome() {
        return nome;
    }

    public EventoBuilder<T> setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public EventoBuilder<T> setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public EventoBuilder<T> setNumeroMaxPartecipanti(int numero) {
        this.numeroMaxPartecipanti = numero;
        return this;
    }

    public DateFormat getData() {
        return data;
    }

    public EventoBuilder<T> setData(DateFormat data) {
        this.data = data;
        return this;
    }

    public POI getPuntoDiInteresse() {
        return puntoDiInteresse;
    }

    public EventoBuilder<T> setPuntoDiInteresse(POI puntoDiInteresse) {
        this.puntoDiInteresse = puntoDiInteresse;
        return this;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public EventoBuilder<T> setAnimatore(Utente animatore) {
        this.animatore = animatore;
        return this;
    }

    public Manifestazione buildManifestazione(Set<Venditore> aziendePartecipanti, Set<Utente> personePartecipanti) {
        return new Manifestazione(this, aziendePartecipanti, personePartecipanti);
    }

    public Visita buildVisita(Set<Utente> personePartecipanti, Proposta proposta) {
        return new Visita(this, personePartecipanti, proposta);
    }
}
