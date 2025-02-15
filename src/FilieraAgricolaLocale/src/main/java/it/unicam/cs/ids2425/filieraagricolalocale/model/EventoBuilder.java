package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class EventoBuilder<T extends EventoBuilder<T>> {

    protected DateFormat data;
    protected String nome;
    protected String descrizione;
    protected int numeroMaxPartecipanti;
    protected POI puntoDiInteresse;
    protected Utente animatore;

    public String getNome() {
        return nome;
    }

    public T setNome(String nome) {
        this.nome = nome;
        return (T) this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public T setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return (T) this;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public T setNumeroMaxPartecipanti(int numero) {
        this.numeroMaxPartecipanti = numero;
        return (T) this;
    }

    public DateFormat getData() {
        return data;
    }

    public T setData(DateFormat data) {
        this.data = data;
        return (T) this;
    }

    public POI getPuntoDiInteresse() {
        return puntoDiInteresse;
    }

    public T setPuntoDiInteresse(POI puntoDiInteresse) {
        this.puntoDiInteresse = puntoDiInteresse;
        return (T) this;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public T setAnimatore(Utente animatore) {
        this.animatore = animatore;
        return (T) this;
    }

    public Manifestazione buildManifestazione(Set<Venditore> aziendePartecipanti, Set<Utente> personePartecipanti) {
        return new Manifestazione(this, aziendePartecipanti, personePartecipanti);
    }

    public Visita buildVisita(Set<Utente> personePartecipanti, Proposta proposta) {
        return new Visita(this, personePartecipanti, proposta);
    }
}
