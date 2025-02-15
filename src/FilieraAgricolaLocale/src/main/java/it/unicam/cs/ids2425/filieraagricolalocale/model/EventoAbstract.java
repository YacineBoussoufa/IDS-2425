package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;

public abstract class EventoAbstract implements Evento {

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
}