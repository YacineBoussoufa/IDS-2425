package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonDeserialize(builder = ProdottoBuilder.class)
public class Prodotto extends Contenuto {

    @ManyToOne(cascade = CascadeType.ALL)
    private POI poi;

    @ManyToMany
    private final Set<Etichetta> listaEtichette = new HashSet<>();
    @ManyToMany
    private final Set<Prodotto> ingredienti = new HashSet<>();

    private final String tipo = "Prodotto";

    /**
     * Costruttore che genera i campi a partire da un builder.
     *
     * @param builder Builder per generare i campi.
     */
    public Prodotto(ProdottoBuilder builder) {

        super(builder);

        this.poi = builder.getPoi();
        this.poi.setTipoPOI(TipoPOI.Prodotto);
        listaEtichette.addAll(builder.getListaEtichette());
        ingredienti.addAll(builder.getIngredienti());

    }

    public Prodotto() {

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
    public void setModifiche(Contenuto contenuto) {

        if (contenuto instanceof Prodotto modifiche) {

            setNome(modifiche.getNome() == null ? getNome() : modifiche.getNome());
            setDescrizione(modifiche.getDescrizione() == null ? getDescrizione() : modifiche.getDescrizione());
            setPrezzo(modifiche.getPrezzo() <= 0 ? getPrezzo() : modifiche.getPrezzo());
            setQuantita(modifiche.quantita <= 0 ? getQuantita() : modifiche.getQuantita());
            setData(modifiche.getData() == null ? getData() : modifiche.getData());
            setPoi(modifiche.getPoi() == null ? getPoi() : modifiche.getPoi());
            setListaEtichette(modifiche.getListaEtichette().isEmpty() ? getListaEtichette() : modifiche.getListaEtichette());
            setIngredienti(modifiche.getIngredienti().isEmpty() ? getIngredienti() : modifiche.getIngredienti());

            //Non è previsto che questi campi mutino
            setId(this.getId());
            setVenditore(this.getVenditore());
        }

    }

    private void setIngredienti(Set<Prodotto> ingredienti) {
        if (ingredienti == null) {
            return;
        }

        this.ingredienti.clear();
        this.ingredienti.addAll(ingredienti);
    }

    private void setListaEtichette(Set<Etichetta> listaEtichette) {
        if (listaEtichette == null) {
            return;
        }

        this.listaEtichette.clear();
        this.listaEtichette.addAll(listaEtichette);
    }

    private void setPoi(POI poi) {
        this.poi = poi;
    }

    @Override
    public String getTipo() {
        return tipo;
    }
    
}
