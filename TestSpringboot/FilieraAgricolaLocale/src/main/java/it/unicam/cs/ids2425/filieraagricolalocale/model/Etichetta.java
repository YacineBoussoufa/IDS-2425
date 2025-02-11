package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.HashSet;
import java.util.Set;

public class Etichetta {

    //todo rimuovere id?
    public static final Set<Etichetta> listaEtichette = new HashSet<Etichetta>();
    private static int numeroEtichette = 0;
    private final int id;
    private String nome;
    private String descrizione;

    public Etichetta(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.id = generateID();
        listaEtichette.add(this);
    }

    /**
     * Assegna il numero attuale di etichette create come ID, poi incrementa il numero.
     *
     * @return ID per l'istanza di Etichetta
     */
    private static synchronized int generateID() {
        return ++numeroEtichette;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getID() {
        return id;
    }
}
