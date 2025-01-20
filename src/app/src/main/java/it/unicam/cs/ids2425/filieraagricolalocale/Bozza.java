package it.unicam.cs.ids2425.filieraagricolalocale;

public class Bozza extends StatoApprovazione {

    protected Bozza(Contenuto contenuto) {
        super(contenuto);
    }

    /**
     * La pubblicazione di una Bozza cambia lo stato in InConvalida.
     */
    @Override
    public void pubblica() {
        contenuto.cambiaStato(new InConvalida(contenuto));
    }
}
