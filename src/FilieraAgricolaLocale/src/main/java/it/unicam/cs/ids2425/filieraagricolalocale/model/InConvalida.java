package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class InConvalida extends StatoApprovazione {

    public InConvalida(Contenuto contenuto) {
        super(contenuto);
    }

    @Override
    Stato statoToString() {
        return Stato.INCONVALIDA;
    }

    /**
     * La pubblicazione di un contenuto in base all'approvazione data ad esso.
     * Se approvato, lo stato diventa Pubblicato.
     * Se non approvato, lo stato diventa Bozza.
     */
    @Override
    public void pubblica() {
        if (contenuto.getApprovazione()) {
            contenuto.cambiaStato(new Pubblicato(contenuto));
        } else
            contenuto.cambiaStato(new Bozza(contenuto));
    }

}
