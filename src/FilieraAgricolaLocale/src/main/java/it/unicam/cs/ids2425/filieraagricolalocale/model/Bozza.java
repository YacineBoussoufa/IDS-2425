package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Bozza extends StatoApprovazione {

    public Bozza(Contenuto contenuto) {
        super(contenuto);
    }

    @Override
    public Stato statoToString() {
        return Stato.BOZZA;
    }

    /**
     * La pubblicazione di una Bozza cambia lo stato in InConvalida.
     */
    @Override
    public void pubblica() {
        contenuto.cambiaStato(new InConvalida(contenuto));
    }
}
