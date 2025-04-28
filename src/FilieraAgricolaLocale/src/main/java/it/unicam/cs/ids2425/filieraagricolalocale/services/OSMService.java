package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OSMService {

    private final VenditoreRepository repoVenditori;
    private final VisitaRepository repoVisite;
    private final ManifestazioneRepository repoManifestazioni;
    private final POIRepository repoPOI;

    @Autowired
    public OSMService(VenditoreRepository repoVenditori,
                      VisitaRepository repoVisite, ManifestazioneRepository repoManifestazioni, POIRepository repoPOI) {
        this.repoVenditori = repoVenditori;
        this.repoVisite = repoVisite;
        this.repoManifestazioni = repoManifestazioni;
        this.repoPOI = repoPOI;
    }

    /**
     * Restituisce tutti i POI della piattaforma
     *
     * @return Tutti i POI della piattaforma
     */
    public List<POI> visualizzaMappa() {

        return repoPOI.findAll();

    }

    /**
     * Visualizza il POI di un venditore
     *
     * @param nome Username del venditore
     * @return POI del venditore
     *
     * @throws VenditoreNonTrovatoException se il nome non è associato a nessun venditore
     */
    public POI visualizzaVenditore(String nome) {
        Venditore venditore = repoVenditori.findById(nome).
                orElseThrow(() -> new VenditoreNonTrovatoException("Non esiste il venditore " + nome));

        return venditore.getLocalizzazione();
    }

    /**
     * Visualizza il POI di tutti i venditori della piattaforma
     *
     * @return Tutti i POI dei venditori
     */
    public List<POI> visualizzaVenditori() {
        List<Venditore> venditore = repoVenditori.findAll();
        List<POI> poiList = new ArrayList<>();

        for (Venditore v : venditore) {
            poiList.add(v.getLocalizzazione());
        }

        return poiList;
    }

    /**
     * Visualizza tutti i POI delle visite
     *
     * @param id Identificatore della visita
     * @return POI della visita
     *
     * @throws EventoNonTrovatoException se l'id non è associato a nessuna visita
     */
    public POI visualizzaVisita(int id) {
        Visita visita = repoVisite.findById(id).
                orElseThrow(() -> new EventoNonTrovatoException("Non esiste la visita con id" + id));

        return visita.getPuntoDiInteresse();
    }

    /**
     * Visualizza tutti i POI delle visite nella piattaforma
     *
     * @return Tutti i POI delle visite
     */
    public List<POI> visualizzaVisite() {
        List<Visita> visite = repoVisite.findAll();
        List<POI> poiList = new ArrayList<>();

        for (Visita v : visite) {
            poiList.add(v.getPuntoDiInteresse());
        }

        return poiList;
    }

    /**
     * Visualizza il POI di una manifestazione
     *
     * @param id Identificatore della manifestazione
     * @return POI della manifestazione
     *
     * @throws EventoNonTrovatoException se l'id non è associato a nessuna manifestazione
     */
    public POI visualizzaManifestazione(int id) {
        Manifestazione manifestazione = repoManifestazioni.findById(id).
                orElseThrow(() -> new EventoNonTrovatoException("Non esiste la manifestazione con id" + id));

        return manifestazione.getPuntoDiInteresse();
    }

    /**
     * Visualizza tutti i POI delle manifestazioni nella piattaforma
     *
     * @return Tutti i POI delle manifestazioni
     */
    public List<POI> visualizzaManifestazioni() {
        List<Manifestazione> manifestazioni = repoManifestazioni.findAll();
        List<POI> poiList = new ArrayList<>();

        for (Manifestazione m : manifestazioni) {
            poiList.add(m.getPuntoDiInteresse());
        }

        return poiList;
    }

    /**
     * Cerca tutti gli oggetti legati a un POI
     *
     * @param poi POI da cercare
     * @return Oggetti legati al POI
     */
    public List<Object> trovaPOI(POI poi) {

        List<Object> poiList = new ArrayList<>();
        //poco efficiente

        for (Venditore venditore : repoVenditori.findAll()) {
            if (venditore.getLocalizzazione().equals(poi)) {
                poiList.add(venditore);
            }
        }

        for (Visita visita : repoVisite.findAll()) {
            if (visita.getPuntoDiInteresse().equals(poi)) {
                poiList.add(visita);
            }
        }

        for (Manifestazione manifestazione : repoManifestazioni.findAll()) {
            if (manifestazione.getPuntoDiInteresse().equals(poi)) {
                poiList.add(manifestazione);
            }
        }

        return poiList;
    }

}
