package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

@JsonDeserialize(builder = ProdottoBuilder.class)
public class Prodotto implements Contenuto {

    //todo gestione id temporanea
    private final int id;
    public int getId() {
        return id;
    }

    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private Venditore venditore;
    private StatoApprovazione statoApprovazione;
    private POI poi;
    private Date data;
    private final Set<Etichetta> listaEtichette = new HashSet<>();
    private final Set<Prodotto> ingredienti = new HashSet<>();
    private final List<LineaOrdine> ordini = new ArrayList<>();

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

        this.statoApprovazione = new Bozza(this);
    }

    @Override
    public StatoApprovazione getStato() {
        return statoApprovazione;
    }

    @Override
    public void cambiaStato(StatoApprovazione stato) {
        this.statoApprovazione = stato;
    }

    public void aggiungiOrdine(LineaOrdine lineaOrdine) {
        ordini.add(lineaOrdine);
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

    public List<LineaOrdine> getOrdini() {
        return ordini;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
}
