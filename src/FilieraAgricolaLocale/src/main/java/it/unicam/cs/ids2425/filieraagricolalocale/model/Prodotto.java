package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonDeserialize(builder = ProdottoBuilder.class)
@DiscriminatorValue("PRODOTTO")
public class Prodotto extends Contenuto {

    @ManyToOne
    private POI poi;

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

        super(builder);

        this.poi = builder.getPoi();
        listaEtichette.addAll(builder.getListaEtichette());
        ingredienti.addAll(builder.getIngredienti());

    }

    public POI getPoi() {
        return poi;
    }

    public Set<Etichetta> getListaEtichette() {
        return listaEtichette;
    }

    public Set<Prodotto> getIngredienti() {
        return ingredienti;
    }

    
}
