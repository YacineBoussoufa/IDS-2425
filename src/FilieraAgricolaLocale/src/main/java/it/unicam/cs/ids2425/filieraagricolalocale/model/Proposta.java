package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idProposta;

    private boolean statoAccettazione;

    @JsonIgnore
    @ManyToOne
    private Utente animatore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Visita visita;

    @JsonIgnore
    @ManyToOne
    private Venditore venditore;

    public Proposta(Utente animatore, Visita visita, Venditore venditore) {
        this.animatore=animatore;
        this.visita=visita;
        this.venditore = venditore;

        this.statoAccettazione=false;
    }

    public Proposta() {

    }

    public boolean getStatoAccettazione() {
        return statoAccettazione;
    }

    public void setStatoAccettazione(boolean statoAccettazione) {
        this.statoAccettazione = statoAccettazione;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public Visita getVisita() {
        return visita;
    }

    public Venditore getVenditore() {
        return venditore;
    }
}
