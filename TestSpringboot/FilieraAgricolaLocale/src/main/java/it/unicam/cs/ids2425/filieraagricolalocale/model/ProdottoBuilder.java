package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
public class ProdottoBuilder {
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo = 0;
    private int quantita = 0;
    private Venditore venditore;
    private POI poi;
    private Date data;
    private final Set<Etichetta> listaEtichette = new HashSet<>();
    private final Set<Prodotto> ingredienti = new HashSet<>();

    public Prodotto build() {
        return new Prodotto(this);
    }

    public static ProdottoBuilder copiaDa(Prodotto prodotto) {
        ProdottoBuilder builder = new ProdottoBuilder();
        builder.setId(prodotto.getId());
        builder.setNome(prodotto.getNome());
        builder.setDescrizione(prodotto.getDescrizione());
        builder.setPrezzo(prodotto.getPrezzo());
        builder.setQuantita(prodotto.getQuantita());
        builder.setVenditore(prodotto.getVenditore());
        builder.setPoi(prodotto.getPoi());
        builder.setData(prodotto.getData());
        builder.setListaEtichette(prodotto.getListaEtichette());
        builder.setIngredienti(prodotto.getIngredienti());
        return builder;
    }

    public int getId() {
        return id;
    }

    public ProdottoBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ProdottoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public ProdottoBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public ProdottoBuilder setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public int getQuantita() {
        return quantita;
    }

    public ProdottoBuilder setQuantita(int quantita) {
        this.quantita = quantita;
        return this;
    }

    public Venditore getVenditore() {
        return venditore;
    }

    public ProdottoBuilder setVenditore(Venditore venditore) {
        this.venditore = venditore;
        return this;
    }

    public POI getPoi() {
        return poi;
    }

    public ProdottoBuilder setPoi(POI poi) {
        this.poi = poi;
        return this;
    }

    public Date getData() {
        return data;
    }

    public ProdottoBuilder setData(Date data) {
        this.data = data;
        return this;
    }

    public Set<Etichetta> getListaEtichette() {
        return listaEtichette;
    }

    public ProdottoBuilder setListaEtichette(Set<Etichetta> listaEtichette) {
        this.listaEtichette.clear();
        this.listaEtichette.addAll(listaEtichette);
        return this;
    }

    public Set<Prodotto> getIngredienti() {
        return ingredienti;
    }

    public ProdottoBuilder setIngredienti(Set<Prodotto> ingredienti) {
        this.ingredienti.clear();
        this.ingredienti.addAll(ingredienti);
        return this;
    }
}
