package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

@JsonDeserialize(builder = ProdottoBuilder.class)
@DiscriminatorValue("PRODOTTO")
public class Prodotto implements Contenuto {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    boolean approvato;

    @ManyToOne
    private Venditore venditore;

    //TODO 
    private StatoApprovazione statoApprovazione;

    @ManyToOne
    private POI poi;
    private Date data;

    @ManyToMany
    private final Set<Etichetta> listaEtichette = new HashSet<>();
    @ManyToMany
    private final Set<Prodotto> ingredienti = new HashSet<>();

    /**
     * Costruttore che genera i campi a partire da un builder.
     *
     * @param builder Builder per generare i campi.
     */
    public Prodotto(ProdottoBuilder builder) {
        this.id = builder.getId();
        this.nome = builder.getNome();
        this.descrizione = builder.getDescrizione();
        this.prezzo = builder.getPrezzo();
        this.quantita = builder.getQuantita();
        this.venditore = builder.getVenditore();
        this.poi = builder.getPoi();
        this.data = builder.getData();
        listaEtichette.addAll(builder.getListaEtichette());
        ingredienti.addAll(builder.getIngredienti());

        approvato = false;
        this.statoApprovazione = new Bozza(this);
    }

    public int getId() {
        return id;
    }

    @Override
    public StatoApprovazione getStato() {
        return statoApprovazione;
    }

    @Override
    public void cambiaStato(StatoApprovazione stato) {
        this.statoApprovazione = stato;
    }

    @Override
    public boolean getApprovazione() {
        return approvato;
    }

    @Override
    public void approva() {
        approvato = true;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    @Override
    public Venditore getVenditore() {
        return venditore;
    }


    public POI getPoi() {
        return poi;
    }


    public Date getData() {
        return data;
    }


    public Set<Etichetta> getListaEtichette() {
        return listaEtichette;
    }

    public Set<Prodotto> getIngredienti() {
        return ingredienti;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
}
