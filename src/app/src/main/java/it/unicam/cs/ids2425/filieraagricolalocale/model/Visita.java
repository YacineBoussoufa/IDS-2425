package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public class Visita implements Evento {

    private DateFormat Data;
    private String Nome;
    private String Descrizione;
    private int NumeroMaxPartecipanti;
    private POI PuntoDiInteresse;
    private Set<Persona> PersonePartecipanti;

    public Visita(DateFormat Data, String Nome, String Descrizione, int NumeroMaxPartecipanti, POI PuntoDiInteresse,
            Set<Persona> PersonePartecipanti){
        this.Data=Data;
        this.Nome=Nome;
        this.Descrizione=Descrizione;
        this.NumeroMaxPartecipanti=NumeroMaxPartecipanti;
        this.PuntoDiInteresse=PuntoDiInteresse;
        this.PersonePartecipanti=PersonePartecipanti;
    }

    @Override
    public String getNome() {
        return Nome;
    }

    @Override
    public void setNome(String Nome) {
        this.Nome=Nome;
    }

    @Override
    public String getDescrizione() {
        return Descrizione;
    }

    @Override
    public void setDescrizione(String Descrizione) {
        this.Descrizione = Descrizione;
    }

    @Override
    public int getNumeroMaxPartecipanti() {
        return NumeroMaxPartecipanti;
    }

    @Override
    public void setNumeroMaxPartecipanti(int NumeroMaxPartecipanti) {
        this.NumeroMaxPartecipanti=NumeroMaxPartecipanti;
    }

    @Override
    public DateFormat getData() {
        return Data;
    }

    @Override
    public void setData(DateFormat Data) {
        this.Data=Data;
    }

    @Override
    public POI getPuntoDiInteresse() {
        return PuntoDiInteresse;
    }

    @Override
    public void setPuntoDiInteresse(POI PuntoDiInteresse) {
        this.PuntoDiInteresse=PuntoDiInteresse;
    }


    public Set<Persona> getPersonePartecipanti() {
        return PersonePartecipanti;
    }
    public void setPersonePartecipanti(Set<Persona> PersonePartecipanti) {
        this.PersonePartecipanti = PersonePartecipanti;
    }

}