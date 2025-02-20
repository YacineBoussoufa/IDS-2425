package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Pubblicato extends StatoApprovazione {

    public Pubblicato(Contenuto contenuto) {
        super(contenuto);
    }

    @Override
    Stato statoToString() {
        return Stato.PUBBLICATO;
    }

    /**
     * Lo stato non cambia dopo la pubblicazione.
     */
    @Override
    public void pubblica() {
    }
}
