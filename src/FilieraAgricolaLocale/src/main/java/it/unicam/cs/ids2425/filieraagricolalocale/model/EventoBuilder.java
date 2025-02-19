package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

public interface EventoBuilder {

    EventoBuilder setDescrizione(String descrizione);
    EventoBuilder setNumeroMaxPartecipanti(int numero);
    EventoBuilder setData(Date data);
    EventoBuilder setPuntoDiInteresse(POI puntoDiInteresse);
    EventoBuilder setAnimatore(Utente animatore);
    EventoBuilder setNome(String nome);

}
