package it.unicam.cs.ids2425.filieraagricolalocale.model;

public class InConvalida extends StatoApprovazione {

    private boolean approvato;

    protected InConvalida(Contenuto contenuto) {
        super(contenuto);
        approvato = false;
    }

    /**
     * La pubblicazione di un contenuto in base all'approvazione data ad esso.
     * Se approvato, lo stato diventa Pubblicato.
     * Se non approvato, lo stato diventa Bozza.
     */
    @Override
    public void pubblica() {
        if (approvato) {
            contenuto.cambiaStato(new Pubblicato(contenuto));
        } else
            contenuto.cambiaStato(new Bozza(contenuto));
    }

    public boolean isApprovato() {
        return approvato;
    }

    /**
     * Permette allo stato InConvalida di diventare Pubblicato
     */
    public void approva() {
        approvato = true;
    }
}
