package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.text.DateFormat;
import java.util.*;

public class EventoService {

    private Map<Integer, Evento> eventi = new HashMap<>();
    private int idCounter = 0;

    //TODO

    public void creaEvento(boolean isManifestazione, DateFormat Data, String Nome, String Descrizione,
                           int NumeroMaxPartecipanti, POI PuntoDiInteresse, Set<Venditore> AziendePartecipanti,
                           Set<Persona> PersonePartecipanti) {

        Evento evento;
        if (isManifestazione) {
            evento = new Manifestazione(Data, Nome, Descrizione, NumeroMaxPartecipanti, PuntoDiInteresse, AziendePartecipanti, PersonePartecipanti);
        } else {
            evento = new Visita(Data, Nome, Descrizione, NumeroMaxPartecipanti, PuntoDiInteresse, PersonePartecipanti);
        }

        eventi.put(idCounter, evento);
        idCounter++;
    }

    public void modificaEvento(int id, DateFormat Data, String Nome, String Descrizione, int NumeroMaxPartecipanti, POI PuntoDiInteresse) {
        Evento evento = eventi.get(id);

        if (evento != null) {
            evento.setData(Data);
            evento.setNome(Nome);
            evento.setDescrizione(Descrizione);
            evento.setNumeroMaxPartecipanti(NumeroMaxPartecipanti);
            evento.setPuntoDiInteresse(PuntoDiInteresse);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void rimuoviEvento(int id) {
        if (!eventi.containsKey(id)) throw new IllegalArgumentException();
        eventi.remove(id);
    }

    public void aggiungiPartecipanti(int id, Set<Persona> nuoviPartecipanti) {
        Evento evento = eventi.get(id);
        if (evento != null) {
            if (evento instanceof Manifestazione) {
                Set<Persona> partecipanti = ((Manifestazione) evento).getPersonePartecipanti();
                partecipanti.addAll(nuoviPartecipanti);
            } else if (evento instanceof Visita) {
                Set<Persona> partecipanti = ((Visita) evento).getPersonePartecipanti();
                partecipanti.addAll(nuoviPartecipanti);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Set<Persona> visualizzaPartecipanti(int id) {
        Evento evento = eventi.get(id);
        if (evento != null) {
            if (evento instanceof Manifestazione) {
                return ((Manifestazione) evento).getPersonePartecipanti();
            } else if (evento instanceof Visita) {
                return ((Visita) evento).getPersonePartecipanti();
            }
        } else {
            throw new IllegalArgumentException();
        }
        return new HashSet<>();
    }

    public Map<Integer, Evento> getEventi() {
        return eventi;
    }

}