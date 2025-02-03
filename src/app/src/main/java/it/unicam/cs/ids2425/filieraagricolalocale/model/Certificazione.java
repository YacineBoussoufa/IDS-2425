package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.HashSet;
import java.util.Set;

public class Certificazione {

    public static final Set<Certificazione> listaCertificazioni = new HashSet<Certificazione>();
    private static int numeroCertificazioni = 0;
    private final int id;
    private String nome;
    private String descrizione;

    public Certificazione(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.id = generateID();
        listaCertificazioni.add(this);
    }

    /**
     * Assegna il numero attuale di certificazioni create come ID, poi incrementa il numero.
     *
     * @return ID per l'istanza di Certificazione
     */
    private static synchronized int generateID() {
        return ++numeroCertificazioni;
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
