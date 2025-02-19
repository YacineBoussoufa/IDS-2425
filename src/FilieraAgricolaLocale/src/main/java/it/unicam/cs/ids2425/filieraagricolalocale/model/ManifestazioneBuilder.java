package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ManifestazioneBuilder implements EventoBuilder {

    private Date data;
    private String nome;
    private String descrizione;
    private int numeroMaxPartecipanti;
    private POI puntoDiInteresse;
    private Utente animatore;

    private Set<Venditore> aziendePartecipanti;
    private Set<Utente> personePartecipanti;

    public Manifestazione build() {
        return new Manifestazione(this);
    }

    public String getNome() {
        return nome;
    }

    public ManifestazioneBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }


    public ManifestazioneBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public ManifestazioneBuilder setNumeroMaxPartecipanti(int numero) {
        this.numeroMaxPartecipanti = numero;
        return this;
    }

    public Date getData() {
        return data;
    }

    public ManifestazioneBuilder setData(Date data) {
        this.data = data;
        return this;
    }

    public POI getPuntoDiInteresse() {
        return puntoDiInteresse;
    }

    public ManifestazioneBuilder setPuntoDiInteresse(POI puntoDiInteresse) {
        this.puntoDiInteresse = puntoDiInteresse;
        return this;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public ManifestazioneBuilder setAnimatore(Utente animatore) {
        this.animatore = animatore;
        return this;
    }

    public Set<Utente> getPersonePartecipanti() {
        return personePartecipanti;
    }

    public ManifestazioneBuilder setPersonePartecipanti(Set<Utente> personePartecipanti) {
        if (this.personePartecipanti == null) {
            this.personePartecipanti = new HashSet<>(); // Initialize if null
        }

        this.personePartecipanti.clear();
        this.personePartecipanti.addAll(personePartecipanti);
        return this;
    }

    public Set<Venditore> getAziendePartecipanti() {
        return aziendePartecipanti;
    }

    public ManifestazioneBuilder setAziendePartecipanti(Set<Venditore> aziendePartecipanti) {
        if (this.aziendePartecipanti == null) {
            this.aziendePartecipanti = new HashSet<>(); // Initialize if null
        }

        this.aziendePartecipanti.clear();
        this.aziendePartecipanti.addAll(aziendePartecipanti);
        return this;
    }

}
