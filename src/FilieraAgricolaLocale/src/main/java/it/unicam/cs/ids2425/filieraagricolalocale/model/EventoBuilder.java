package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.text.DateFormat;
import java.util.Set;

public interface EventoBuilder {

    EventoBuilder setDescrizione(String descrizione);
    EventoBuilder setNumeroMaxPartecipanti(int numero);
    EventoBuilder setData(DateFormat data);
    EventoBuilder setPuntoDiInteresse(POI puntoDiInteresse);
    EventoBuilder setAnimatore(Utente animatore);
    EventoBuilder setNome(String nome);

}
