package it.unicam.cs.ids2425.filieraagricolalocale.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public abstract class StatoApprovazione {

    @Id
    @OneToOne
    protected final Contenuto contenuto;

    protected StatoApprovazione(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    abstract Stato statoToString();

    /**
     * L'implementazione del metodo varia per lo stato del contenuto.
     */
    public abstract void pubblica();

}
