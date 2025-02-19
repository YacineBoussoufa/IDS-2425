package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.time.LocalDate;

public abstract class StatoApprovazione {

    protected final Contenuto contenuto;

    protected StatoApprovazione(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    abstract String statoToString();

    /**
     * L'implementazione del metodo varia per lo stato del contenuto.
     */
    public abstract void pubblica();

}
