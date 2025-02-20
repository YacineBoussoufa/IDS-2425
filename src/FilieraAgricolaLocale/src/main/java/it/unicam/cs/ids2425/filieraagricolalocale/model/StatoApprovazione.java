package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bozza.class, name = "Bozza"),
        @JsonSubTypes.Type(value = InConvalida.class, name = "InConvalida"),
        @JsonSubTypes.Type(value = Pubblicato.class, name = "Pubblicato")
})
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
