package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;

public abstract class EventoAbstract {

    private DateFormat data;
    private String nome;
    private String descrizione;
    private int numeroMaxPartecipanti;
    private POI puntoDiInteresse;
    private Utente animatore;

    public EventoAbstract(ManifestazioneBuilder builder) {
        this.data = builder.getData();
        this.nome = builder.getNome();
        this.descrizione = builder.getDescrizione();
        this.numeroMaxPartecipanti = builder.getNumeroMaxPartecipanti();
        this.puntoDiInteresse = builder.getPuntoDiInteresse();
        this.animatore = builder.getAnimatore();
    }

    public EventoAbstract(VisitaBuilder builder) {
        this.data = builder.getData();
        this.nome = builder.getNome();
        this.descrizione = builder.getDescrizione();
        this.numeroMaxPartecipanti = builder.getNumeroMaxPartecipanti();
        this.puntoDiInteresse = builder.getPuntoDiInteresse();
        this.animatore = builder.getAnimatore();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome=nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }

    public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti=numeroMaxPartecipanti;
    }

    public DateFormat getData() {
        return data;
    }

    public void setData(DateFormat data) {
        this.data=data;
    }

    public POI getPuntoDiInteresse() {
        return puntoDiInteresse;
    }

    public void setPuntoDiInteresse(POI puntoDiInteresse) {
        this.puntoDiInteresse=puntoDiInteresse;
    }

    public Utente getAnimatore() {
        return this.animatore;
    }

}