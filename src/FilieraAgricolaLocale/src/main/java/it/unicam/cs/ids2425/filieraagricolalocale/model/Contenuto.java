package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

//@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
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

    void approva();

    boolean getApprovazione();

    /**
     * @return
     */
    int getId();

    /**
     * @return
     */
    String getNome();

    /**
     * @return
     */
    String getDescrizione();

    /**
     * @return
     */
    double getPrezzo();

    /**
     * @return
     */
    int getQuantita();

    /**
     * @return
     */
    Date getData();

    /**
     * @return venditore associato
     */
    Venditore getVenditore();

    /**
     * @return
     */
    //TipoContenuto getTipoContenuto();

}
