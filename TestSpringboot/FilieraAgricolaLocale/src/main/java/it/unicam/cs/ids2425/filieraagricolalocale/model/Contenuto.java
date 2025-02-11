package it.unicam.cs.ids2425.filieraagricolalocale.model;

public interface Contenuto {

    /**
     * Restituisce lo stato di approvazione attuale del contenuto.
     *
     * @return Stato del Contenuto.
     */
    StatoApprovazione getStato();

    /**
     * Cambia lo stato di approvazione del contenuto.
     *
     * @param stato Stato da inserire nel Contenuto
     */
    void cambiaStato(StatoApprovazione stato);

}
