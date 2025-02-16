package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEventoDati;

import java.util.*;

public class EventoService {

    private Map<Integer, Visita> repoVisite = new HashMap<>();
    private Map<Integer, Manifestazione> repoManifestazioni = new HashMap<>();
    private int idCounterVisite = 0;
    private int idCounterManifestazioni = 0;
    private MiddlewareEventoDati middleware;

    public void aggiungiEvento(EventoAbstract evento) {

        if (!middleware.check(evento)) {
            throw new DatiIncorrettiException();
        }

        if(evento instanceof Visita)
            repoVisite.put(idCounterVisite++, (Visita) evento);
        else if(evento instanceof Manifestazione)
            repoManifestazioni.put(idCounterManifestazioni++, (Manifestazione) evento);
        else
            throw new DatiIncorrettiException();

    }

    public void modificaEvento(int id, EventoAbstract eventoModificato) {

        if ((!repoManifestazioni.containsKey(id)) && (!repoVisite.containsKey(id))) {
            throw new DatiIncorrettiException();
        }

        if (!middleware.check(eventoModificato)) {
            throw new DatiIncorrettiException();
        }

        if(eventoModificato instanceof Visita)
            repoVisite.put(id, (Visita) eventoModificato);
        else if (eventoModificato instanceof Manifestazione)
            repoManifestazioni.put(id, (Manifestazione) eventoModificato);
        else
            throw new DatiIncorrettiException();
    }

    public void rimuoviVisita(int id) {
        if (!repoVisite.containsKey(id)) {
            throw new DatiIncorrettiException();
        }
        repoVisite.remove(id);
    }

    public void rimuoviManifestazione(int id) {
        if (!repoManifestazioni.containsKey(id)) {
            throw new DatiIncorrettiException();
        }
        repoManifestazioni.remove(id);
    }

    public void aggiungiUtentePartecipanteVisita(int id, Utente utente) {
        Visita visita = repoVisite.get(id);
        if (visita != null) {
            visita.getPersonePartecipanti().add(utente);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void aggiungiUtentePartecipanteManifestazione(int id, Utente utente) {
        Manifestazione manifestazione = repoManifestazioni.get(id);
        if (manifestazione != null) {
            manifestazione.getPersonePartecipanti().add(utente);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void aggiungiAziendaPartecipanteManifestazione(int id, Venditore azienda) {
        Manifestazione manifestazione = repoManifestazioni.get(id);
        if (manifestazione != null) {
            manifestazione.getAziendePartecipanti().add(azienda);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void aggiungiUtentiPartecipantiVisita(int id, Set<Utente> nuoviPartecipanti) {
        Visita visita = (Visita) repoVisite.get(id);
        if (visita != null) {
            visita.getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void aggiungiUtentiPartecipantiManifestazione(int id, Set<Utente> nuoviPartecipanti) {
        Manifestazione manifestazione = repoManifestazioni.get(id);
        if (manifestazione != null) {
            manifestazione.getPersonePartecipanti().addAll(nuoviPartecipanti);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public void aggiungiAziendePartecipantiManifestazione(int id, Set<Venditore> nuoviPartecipanti) {
        Manifestazione manifestazione = repoManifestazioni.get(id);
        if (manifestazione != null) {
            manifestazione.getAziendePartecipanti().addAll(nuoviPartecipanti);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    public Set<Utente> visualizzaUtentiPartecipantiVisita(int id) {
        Visita visita = repoVisite.get(id);

        if (visita != null) {
            return visita.getPersonePartecipanti();
        }
        throw new DatiIncorrettiException();
    }

    public Set<Utente> visualizzaUtentiPartecipantiManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.get(id);

        if (manifestazione != null) {
            return manifestazione.getPersonePartecipanti();
        }
        throw new DatiIncorrettiException();
    }

    public Set<Venditore> visualizzaAziendePartecipantiManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.get(id);

        if (manifestazione != null) {
            return manifestazione.getAziendePartecipanti();
        }
        throw new DatiIncorrettiException();
    }

    public void accettaProposta(int id) {
        Visita visita = repoVisite.get(id);

        if (visita == null) {
            throw new DatiIncorrettiException();
        }

        Proposta proposta = visita.getProposta();
        if (proposta == null) {
            throw new DatiIncorrettiException();
        }

        proposta.setStatoAccettazione(true);

        if (!middleware.check(visita)) {
            throw new DatiIncorrettiException();
        }
    }

    public Map<Integer, Visita> getRepoVisite() {
        return repoVisite;
    }

    public Map<Integer, Manifestazione> getRepoManifestazioni() {
        return repoManifestazioni;
    }

}