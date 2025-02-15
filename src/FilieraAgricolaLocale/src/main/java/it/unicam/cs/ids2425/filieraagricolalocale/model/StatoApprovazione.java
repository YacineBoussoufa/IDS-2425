package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.time.LocalDate;

public abstract class StatoApprovazione {

    protected final Contenuto contenuto;
    private final LocalDate dataCreazione;

    protected StatoApprovazione(Contenuto contenuto) {
        this.dataCreazione = LocalDate.now();
        this.contenuto = contenuto;
    }

    abstract String statoToString();

    /**
     * L'implementazione del metodo varia per lo stato del contenuto.
     */
    public abstract void pubblica();

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }
}
