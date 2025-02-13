package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Proposta {

    private boolean statoAccettazione;
    private Utente animatore;
    private Visita visita;
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
