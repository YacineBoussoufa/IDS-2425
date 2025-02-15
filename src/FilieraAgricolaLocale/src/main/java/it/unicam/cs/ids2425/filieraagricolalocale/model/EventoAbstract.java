package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;

public abstract class EventoAbstract {

    protected DateFormat data;
    protected String nome;
    protected String descrizione;
    protected int numeroMaxPartecipanti;
    protected POI puntoDiInteresse;
    protected Utente animatore;

    protected EventoAbstract(EventoBuilder<?> builder) {
        this.data = builder.data;
        this.nome = builder.nome;
        this.descrizione = builder.descrizione;
        this.numeroMaxPartecipanti = builder.numeroMaxPartecipanti;
        this.puntoDiInteresse = builder.puntoDiInteresse;
        this.animatore = builder.animatore;
    }

    public abstract String getNome();

    public abstract void setNome(String Nome);

    public abstract String getDescrizione();

    public abstract void setDescrizione(String Descrizione);

    public abstract int getNumeroMaxPartecipanti();

    public abstract void setNumeroMaxPartecipanti(int NumeroMaxPartecipanti);

    public abstract DateFormat getData();

    public abstract void setData(DateFormat Data);

    public abstract POI getPuntoDiInteresse();

    public abstract void setPuntoDiInteresse(POI PuntoDiInteresse);

    public abstract Utente getAnimatore();
}