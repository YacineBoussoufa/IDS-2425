package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Pubblicato extends StatoApprovazione {

    public Pubblicato(Contenuto contenuto) {
        super(contenuto);
    }

    @Override
    String statoToString() {
        return "Pubblicato";
    }

    /**
     * Lo stato non cambia dopo la pubblicazione.
     */
    @Override
    public void pubblica() {
    }
}
