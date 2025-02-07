package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Proposta {

    private int Titolo;
    private String Descrizione;
    private boolean StatoAccettazione;
    private Persona Animatore;
    private Visita visita;
    private Venditore venditore;

    public Proposta(int Titolo, String Descrizione, Persona Animatore, Visita visita, Venditore venditore) {
        this.Titolo=Titolo;
        this.Descrizione=Descrizione;
        this.Animatore=Animatore;
        this.visita=visita;
        this.venditore=venditore;
        StatoAccettazione=false;
    }

    public int getTitolo() {
        return Titolo;
    }

    public void setTitolo(int Titolo) {
        this.Titolo = Titolo;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String Descrizione) {
        this.Descrizione = Descrizione;
    }

    public boolean getStatoAccettazione() {
        return StatoAccettazione;
    }

    public void setStatoAccettazione(boolean StatoAccettazione) {
        this.StatoAccettazione = StatoAccettazione;
    }

    public Persona getAnimatore() {
        return Animatore;
    }

    public Visita getVisita() {
        return visita;
    }

    public Venditore getVenditore() {
        return venditore;
    }
}
