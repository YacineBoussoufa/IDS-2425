package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Proposta {

    private boolean statoAccettazione;
    @ManyToOne
    private Utente animatore;

    @JsonBackReference
    @Id
    @OneToOne
    private Visita visita;
    @ManyToOne
    private Venditore venditore;

    public Proposta(Utente animatore, Visita visita, Venditore venditore) {
        this.animatore=animatore;
        this.visita=visita;
        this.venditore = venditore;
        this.statoAccettazione=false;
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
