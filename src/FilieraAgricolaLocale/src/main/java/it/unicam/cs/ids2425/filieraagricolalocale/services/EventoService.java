package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ManifestazioneRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VisitaRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventoService {

    //private Map<Integer, Visita> repoVisite = new HashMap<>();
    //private Map<Integer, Manifestazione> repoManifestazioni = new HashMap<>();

    private VisitaRepository repoVisite;
    private ManifestazioneRepository repoManifestazioni;
    private MiddlewareEvento middleware;

    @Autowired
    public EventoService(VisitaRepository v, ManifestazioneRepository m) {
        repoVisite = v;
        repoManifestazioni = m;
    }

    public void setMiddleware(MiddlewareEvento m){
        this.middleware = m;
    }

    public void aggiungiEventoV(Visita evento) {

        if (!middleware.check(evento)) {
            throw new DatiIncorrettiException();
        }

        repoVisite.save(evento);

    }

    public void aggiungiEventoM(Manifestazione evento) {

        if (!middleware.check(evento)) {
            throw new DatiIncorrettiException();
        }

        repoManifestazioni.save(evento);

    }

    public void modificaEvento(int id, EventoAbstract eventoModificato) {

        if ((repoManifestazioni.findById(id).isEmpty()) && (repoVisite.findById(id).isEmpty())) {
            throw new DatiIncorrettiException();
        }

        if (!middleware.check(eventoModificato)) {
            throw new DatiIncorrettiException();
        }

        if(eventoModificato instanceof Visita)
            repoVisite.save((Visita) eventoModificato);
        else if (eventoModificato instanceof Manifestazione)
            repoManifestazioni.save((Manifestazione) eventoModificato);
        else
            throw new DatiIncorrettiException();
    }

    public void rimuoviVisita(int id) {
        if (repoVisite.findById(id).isEmpty()) {
            throw new DatiIncorrettiException();
        }
        repoVisite.deleteById(id);
    }

    public void rimuoviManifestazione(int id) {
        if (repoManifestazioni.findById(id).isEmpty()) {
            throw new DatiIncorrettiException();
        }
        repoManifestazioni.deleteById(id);
    }

    public void aggiungiUtentePartecipanteAVisita(int id, Utente utente) {
        Visita visita = repoVisite.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());
        visita.getPersonePartecipanti().add(utente);
        repoVisite.save(visita);
    }

    public void aggiungiUtentePartecipanteAManifestazione(int id, Utente utente) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());
        manifestazione.getPersonePartecipanti().add(utente);
        repoManifestazioni.save(manifestazione);
    }

    public void aggiungiAziendaPartecipanteAManifestazione(int id, Venditore azienda) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());
        manifestazione.getAziendePartecipanti().add(azienda);
        repoManifestazioni.save(manifestazione);
    }

    public void aggiungiUtentiPartecipantiAVisita(int id, Set<Utente> nuoviPartecipanti) {
        Visita visita =  repoVisite.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        visita.getPersonePartecipanti().addAll(nuoviPartecipanti);
        repoVisite.save(visita);
    }

    public void aggiungiUtentiPartecipantiAManifestazione(int id, Set<Utente> nuoviPartecipanti) {
        Manifestazione manifestazione =  repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        manifestazione.getPersonePartecipanti().addAll(nuoviPartecipanti);
        repoManifestazioni.save(manifestazione);
    }

    public void aggiungiAziendePartecipantiAManifestazione(int id, Set<Venditore> nuoviPartecipanti) {
        Manifestazione manifestazione =  repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        manifestazione.getAziendePartecipanti().addAll(nuoviPartecipanti);
        repoManifestazioni.save(manifestazione);
    }

    public Set<Utente> visualizzaUtentiPartecipantiAVisita(int id) {
        Visita visita = repoVisite.findById(id).orElseThrow(() -> new DatiIncorrettiException());

        return visita.getPersonePartecipanti();

    }

    public Set<Utente> visualizzaUtentiPartecipantiAManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        return manifestazione.getPersonePartecipanti();
    }

    public Set<Venditore> visualizzaAziendePartecipantiAManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        return manifestazione.getAziendePartecipanti();
    }

    public void accettaProposta(int id) {
        Visita visita = repoVisite.findById(id)
                .orElseThrow(() -> new DatiIncorrettiException());

        Proposta proposta = visita.getProposta();
        if (proposta == null) {
            throw new DatiIncorrettiException();
        }

        proposta.setStatoAccettazione(true);

        /*
        if (!middleware.check(visita)) {
            throw new DatiIncorrettiException();
        }*/

    }

    public List<Visita> getRepoVisite() {
        return repoVisite.findAll();
    }

    public List<Manifestazione> getRepoManifestazioni() {
        return repoManifestazioni.findAll();
    }

    public List<Visita> getRepoVisiteAccettate() {
        List<Visita> visiteAccettate = new ArrayList<>();

        for (Visita visita : repoVisite.findAll()) {
            Proposta proposta = visita.getProposta();
            if (proposta != null && proposta.getStatoAccettazione()) {
                visiteAccettate.add(visita);
            }
        }
        return visiteAccettate;
    }

    public List<Visita> getRepoVisiteNonAccettate() {
        List<Visita> visiteRifiutate = new ArrayList<>();

        for (Visita visita : repoVisite.findAll()) {
            Proposta proposta = visita.getProposta();
            if (proposta != null && !proposta.getStatoAccettazione()) {
                visiteRifiutate.add(visita);
            }
        }

        return visiteRifiutate;
    }

}