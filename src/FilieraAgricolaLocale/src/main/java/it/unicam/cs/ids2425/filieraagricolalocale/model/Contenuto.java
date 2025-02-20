package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.util.ContenutoDeserializer;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@JsonDeserialize(using = ContenutoDeserializer.class)
public abstract class Contenuto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String nome;
    protected String descrizione;
    protected double prezzo;
    protected boolean approvato;
    protected Date data;

    @Enumerated(EnumType.STRING)
    protected Stato statoContenuto;

    @Transient
    protected StatoApprovazione statoApprovazione;

    @ManyToOne
    protected Venditore venditore;
    protected int quantita;


    protected Contenuto(ContenutoBuilder builder) {
        this.data = builder.getData();
        this.nome = builder.getNome();
        this.descrizione = builder.getDescrizione();
        this.prezzo = builder.getPrezzo();
        this.approvato = false;
        this.data = builder.getData();
        this.statoApprovazione = new Bozza(this);
        this.quantita = builder.getQuantita();
        this.venditore = builder.getVenditore();
    }

    public Contenuto() {

    }

    // Initialize the state based on the stored value
    @PostLoad
    public void initializeState() {
        switch (statoContenuto) {
            case Stato.BOZZA:
                statoApprovazione = new Bozza(this);
                break;
            case Stato.INCONVALIDA:
                statoApprovazione = new InConvalida(this);
                break;
            case Stato.PUBBLICATO:
                statoApprovazione = new Pubblicato(this);
                break;
            default:
                throw new IllegalStateException("Unknown state: " + statoContenuto);
        }
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Restituisce lo stato di approvazione attuale del contenuto.
     *
     * @return Stato del Contenuto.
     */
    public StatoApprovazione getStato() {
        return statoApprovazione;
    }

    /**
     * Cambia lo stato di approvazione del contenuto.
     *
     * @param stato Stato da inserire nel Contenuto
     */
    public void cambiaStato(StatoApprovazione stato) {
        this.statoApprovazione = stato;
    }

    public void approva() {
        this.approvato = true;
    }

    public boolean getApprovazione() {
        return approvato;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Venditore getVenditore() {
       return venditore;
    }
 
    public void setVenditore(Venditore venditore) {
       this.venditore = venditore;
    }

    public int getQuantita() {
       return this.quantita;
    }
 
    public void setQuantita(int q) {
       this.quantita = q;
    }

    public abstract Contenuto setModifiche(Contenuto contenuto);

}
