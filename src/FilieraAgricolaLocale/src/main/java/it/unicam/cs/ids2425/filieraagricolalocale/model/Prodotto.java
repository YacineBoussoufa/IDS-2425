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

    @Override
    public Prodotto setModifiche(Contenuto contenuto) {

        if (contenuto instanceof Prodotto modifiche) {
            ProdottoBuilder builder = ProdottoBuilder.copiaDa(modifiche);

            builder.setNome(modifiche.getNome() == null ? getNome() : modifiche.getNome());
            builder.setDescrizione(modifiche.getDescrizione() == null ? getDescrizione() : modifiche.getDescrizione());
            builder.setPrezzo(modifiche.getPrezzo() == 0 ? getPrezzo() : modifiche.getPrezzo());
            builder.setQuantita(modifiche.quantita == 0 ? getQuantita() : modifiche.getQuantita());
            builder.setData(modifiche.getData() == null ? getData() : modifiche.getData());
            builder.setPoi(modifiche.getPoi() == null ? getPoi() : modifiche.getPoi());
            builder.setListaEtichette(modifiche.getListaEtichette().isEmpty() ? getListaEtichette() : modifiche.getListaEtichette());
            builder.setIngredienti(modifiche.getIngredienti().isEmpty() ? getIngredienti() : modifiche.getIngredienti());

            //Non è previsto che questi campi mutino
            builder.setId(getId());
            builder.setVenditore(getVenditore());

            return builder.build();
        }

        return null;

    }
    
}
