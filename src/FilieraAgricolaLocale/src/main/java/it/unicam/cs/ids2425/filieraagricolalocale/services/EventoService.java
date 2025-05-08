package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NumeroMassimoUtentiException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.ManifestazioneRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.VisitaRepository;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class EventoService {

    private final VisitaRepository repoVisite;
    private final ManifestazioneRepository repoManifestazioni;

    private MiddlewareEvento middleware;

    @Autowired
    public EventoService(VisitaRepository v, ManifestazioneRepository m) {
        repoVisite = v;
        repoManifestazioni = m;
    }

    public void setMiddleware(MiddlewareEvento m){
        this.middleware = m;
    }

    /**
     * @param evento
     */
    public void aggiungiEventoV(Visita evento) {

        if (!middleware.check(evento)) {
            throw new DatiIncorrettiException("Errore presente nei dati");
        }

        repoVisite.save(evento);

    }

    /**
     * @param evento
     */
    public void aggiungiEventoM(Manifestazione evento) {

        if (!middleware.check(evento)) {
            throw new DatiIncorrettiException("Errore presente nei dati");
        }

        repoManifestazioni.save(evento);

    }

    public Visita getVisita(int id) {
        return repoVisite.findById(id).orElseThrow(() -> new EventoNonTrovatoException("Non esiste visita con id " + id));
    }

    public Manifestazione getManifestazione(int id) {
        return repoManifestazioni.findById(id).orElseThrow(() -> new EventoNonTrovatoException("Non esiste manifestazione con id " + id));
    }

    /**
     * @param id
     * @param eventoModificato
     */
    public void modificaEvento(int id, EventoAbstract eventoModificato) {

        if ((repoManifestazioni.findById(id).isEmpty()) && (repoVisite.findById(id).isEmpty())) {
            throw new EventoNonTrovatoException("Non esiste evento con id " + id);
        }

        if (!middleware.check(eventoModificato)) {
            throw new DatiIncorrettiException("Dati dell'evento incorretti");
        }

        if(eventoModificato instanceof Visita) {
            repoVisite.save((Visita) eventoModificato);
        }
        else if (eventoModificato instanceof Manifestazione) {
            repoManifestazioni.save((Manifestazione) eventoModificato);
        }
        else
            throw new DatiIncorrettiException();
    }

    /**
     * @param id
     */
    public void rimuoviVisita(int id) {
        if (repoVisite.findById(id).isEmpty()) {
            throw new EventoNonTrovatoException("Non esiste evento con id "+ id);
        }
        repoVisite.deleteById(id);
    }

    /**
     * @param id
     */
    public void rimuoviManifestazione(int id) {
        if (repoManifestazioni.findById(id).isEmpty()) {
            throw new EventoNonTrovatoException("Non esiste evento con id "+ id);
        }
        repoManifestazioni.deleteById(id);
    }

    /**
     * @param id
     * @param utente
     */
    public void aggiungiUtentePartecipanteAVisita(int id, Utente utente) {
        Visita visita = repoVisite.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if (visita.getNumeroPartecipanti() > visita.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(utente == null) throw new DatiIncorrettiException("Utente non esiste");
        

        visita.getPersonePartecipanti().add(utente);
        repoVisite.save(visita);
    }

    /**
     * @param id
     * @param utente
     */
    public void aggiungiUtentePartecipanteAManifestazione(int id, Utente utente) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if(manifestazione.getNumeroPartecipanti() > manifestazione.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(utente == null) throw new DatiIncorrettiException("Utente non esiste");
        
        manifestazione.getPersonePartecipanti().add(utente);
        repoManifestazioni.save(manifestazione);
    }

    /**
     * @param id
     * @param azienda
     */
    public void aggiungiAziendaPartecipanteAManifestazione(int id, Venditore azienda) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if(manifestazione.getNumeroPartecipanti() > manifestazione.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(azienda == null) throw new DatiIncorrettiException("venditore non esiste");
        
        manifestazione.getAziendePartecipanti().add(azienda);
        repoManifestazioni.save(manifestazione);
    }

    /**
     * @param id
     * @param nuoviPartecipanti
     */
    public void aggiungiUtentiPartecipantiAVisita(int id, Set<Utente> nuoviPartecipanti) {
        Visita visita =  repoVisite.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if((visita.getNumeroPartecipanti() + nuoviPartecipanti.size()) > visita.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(nuoviPartecipanti.isEmpty()) throw new DatiIncorrettiException("0 utenti trovati");

        visita.getPersonePartecipanti().addAll(nuoviPartecipanti);
        repoVisite.save(visita);
    }

    /**
     * @param id
     * @param nuoviPartecipanti
     */
    public void aggiungiUtentiPartecipantiAManifestazione(int id, Set<Utente> nuoviPartecipanti) {
        Manifestazione manifestazione =  repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if((manifestazione.getNumeroPartecipanti()+nuoviPartecipanti.size()) > manifestazione.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(nuoviPartecipanti.isEmpty()) throw new DatiIncorrettiException("0 utenti trovati");

        manifestazione.getPersonePartecipanti().addAll(nuoviPartecipanti);
        repoManifestazioni.save(manifestazione);
    }

    /**
     * @param id
     * @param nuoviPartecipanti
     */
    public void aggiungiAziendePartecipantiAManifestazione(int id, Set<Venditore> nuoviPartecipanti) {
        Manifestazione manifestazione =  repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        if((manifestazione.getNumeroPartecipanti()+nuoviPartecipanti.size()) > manifestazione.getNumeroMaxPartecipanti() )
            throw new NumeroMassimoUtentiException("Numero massimo di utenti raggiunto");
        if(nuoviPartecipanti.isEmpty()) throw new DatiIncorrettiException("0 utenti trovati");

        manifestazione.getAziendePartecipanti().addAll(nuoviPartecipanti);
        repoManifestazioni.save(manifestazione);
    }

    /**
     * @param id
     * @return
     */
    public Set<Utente> visualizzaUtentiPartecipantiAVisita(int id) {
        Visita visita = repoVisite.findById(id).orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        return visita.getPersonePartecipanti();

    }

    /**
     * @param id
     * @return
     */
    public Set<Utente> visualizzaUtentiPartecipantiAManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        return manifestazione.getPersonePartecipanti();
    }

    /**
     * @param id
     * @return
     */
    public Set<Venditore> visualizzaAziendePartecipantiAManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        return manifestazione.getAziendePartecipanti();
    }

    /**
     * @param id
     */
    public void accettaProposta(int id) {
        Visita visita = repoVisite.findById(id)
                .orElseThrow(() -> new EventoNonTrovatoException("Non esiste evento con id "+ id));

        Proposta proposta = visita.getProposta();
        if (proposta == null) {
            throw new DatiIncorrettiException();
        }

        proposta.setStatoAccettazione(true);

        if (!middleware.check(visita)) {
            throw new DatiIncorrettiException();
        }

        repoVisite.save(visita);
    }

    public List<Visita> getRepoVisite() {
        return repoVisite.findAll();
    }

    public List<Manifestazione> getRepoManifestazioni() {
        List<Manifestazione> listaManifestazioni = new ArrayList<>();

        for(Manifestazione manifestazione : repoManifestazioni.findAll())
            if(manifestazione.getData().after(Date.from(Instant.now())))
                listaManifestazioni.add(manifestazione);

        return listaManifestazioni;
    }

    public List<Visita> getRepoVisiteAccettate() {
        List<Visita> visiteAccettate = new ArrayList<>();

        for (Visita visita : repoVisite.findAll()) {
            Proposta proposta = visita.getProposta();
            if (proposta != null && proposta.getStatoAccettazione() && visita.getData().after(Date.from(Instant.now()) )) {
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

    public Collection<Visita> visualizzaVisiteAnimatore(String id){
        Collection<Visita> s = new LinkedList<>();
        repoVisite.findAll().forEach(o -> {if(o.getAnimatore().getUsername().equals(id)) s.add(o);});
        return s;
    }

    public Collection<Manifestazione> visualizzaManifestazioniAnimatore(String id){
        Collection<Manifestazione> s = new LinkedList<>();
        repoManifestazioni.findAll().forEach(o -> {if(o.getAnimatore().getUsername().equals(id)) s.add(o);});
        return s;
    }


}