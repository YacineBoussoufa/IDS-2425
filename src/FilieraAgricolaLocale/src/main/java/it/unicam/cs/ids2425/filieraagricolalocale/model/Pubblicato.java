package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class Pubblicato extends StatoApprovazione {

    protected Pubblicato(Contenuto contenuto) {
        super(contenuto);
    }


    /**
     * Lo stato non cambia dopo la pubblicazione.
     */
    @Override
    public void pubblica() {
    }
}
