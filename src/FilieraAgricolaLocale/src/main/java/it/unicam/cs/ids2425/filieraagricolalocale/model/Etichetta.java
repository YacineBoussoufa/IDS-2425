package it.unicam.cs.ids2425.filieraagricolalocale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etichetta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private TipoEtichetta tipo;

    public Etichetta(String nome, String descrizione, TipoEtichetta t) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = t;
    }

    public Etichetta() {

    }

    public TipoEtichetta getTipo() {
        return tipo;
    }

    public void setTipo(TipoEtichetta tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getID() {
        return id;
    }
}
