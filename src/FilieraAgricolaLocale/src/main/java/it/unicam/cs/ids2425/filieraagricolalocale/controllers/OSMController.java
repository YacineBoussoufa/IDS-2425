package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.EventoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.services.OSMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mappa")
public class OSMController {

    private OSMService os;

    @Autowired
    public OSMController(OSMService os) {
        this.os = os;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getMap() {

        try {
            List<POI> poiList = os.visualizzaMappa();
            return new ResponseEntity<>(poiList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/prodotto", method = RequestMethod.GET)
    public ResponseEntity<Object> findProduct(@RequestBody int id) {

        try {
            POI poi = os.visualizzaProdotto(id);
            return new ResponseEntity<>(poi, HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/prodotti", method = RequestMethod.GET)
    public ResponseEntity<Object> findProducts() {

        try {
            List<POI> poiList = os.visualizzaProdotti();
            return new ResponseEntity<>(poiList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/venditore", method = RequestMethod.GET)
    public ResponseEntity<Object> findVendor(@RequestBody String username) {

        try {
            POI poi = os.visualizzaVenditore(username);
            return new ResponseEntity<>(poi, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/venditori", method = RequestMethod.GET)
    public ResponseEntity<Object> findVendors() {

        try {
            List<POI> poiList = os.visualizzaVenditori();
            return new ResponseEntity<>(poiList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/visita", method = RequestMethod.GET)
    public ResponseEntity<Object> findVisit(@RequestBody int id) {

        try {
            POI poi = os.visualizzaVisita(id);
            return new ResponseEntity<>(poi, HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/visite", method = RequestMethod.GET)
    public ResponseEntity<Object> findVisits() {

        try {
            List<POI> poiList = os.visualizzaVisite();
            return new ResponseEntity<>(poiList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/manifestazione", method = RequestMethod.GET)
    public ResponseEntity<Object> findManifestation(@RequestBody int id) {

        try {
            POI poi = os.visualizzaManifestazione(id);
            return new ResponseEntity<>(poi, HttpStatus.OK);
        } catch (EventoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/manifestazioni", method = RequestMethod.GET)
    public ResponseEntity<Object> findManifestations() {

        try {
            List<POI> poiList = os.visualizzaManifestazioni();
            return new ResponseEntity<>(poiList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<Object> findPOI(@RequestBody POI poi) {

        try {
            List<Object> listObjects = os.trovaPOI(poi);
            return new ResponseEntity<>(listObjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
