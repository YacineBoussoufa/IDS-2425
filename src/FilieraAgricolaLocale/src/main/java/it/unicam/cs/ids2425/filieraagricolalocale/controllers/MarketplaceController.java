package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.VenditoreNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketplace")
public class MarketplaceController {

    private MarketplaceService ms;

    @Autowired
    public MarketplaceController(MarketplaceService ms) {
        this.ms = ms;
    }

    /*
     * Ottiene un prodotto in GET con il suo id
     */
    @RequestMapping(value = "/prodotti/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable int id) {

        try {
            Prodotto prodotto = ms.visualizzaProdotto(id);
            return new ResponseEntity<>(prodotto, HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei prodotti in GET
     */
    @RequestMapping(value = "/prodotti", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(ms.visualizzaProdotti(), HttpStatus.OK);
    }

    /*
     * Ottiene un prodotto in GET con il suo id
     */
    @RequestMapping(value = "/pacchetto/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPackage(@PathVariable int id) {

        try {
            Pacchetto pacchetto = ms.visualizzaPacchetto(id);
            return new ResponseEntity<>(pacchetto, HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei pacchetti in GET
     */
    @RequestMapping(value = "/pacchetti", method = RequestMethod.GET)
    public ResponseEntity<Object> getPackages() {
        return new ResponseEntity<>(ms.visualizzaPacchetti(), HttpStatus.OK);
    }

    /*
     * Ottieni un venditore in GET con il suo username
     */
    @RequestMapping(value = "/venditori/{username}", method = RequestMethod.GET)
    public ResponseEntity<Object> getVendor(@PathVariable String username) {

        try {
            Venditore venditore = ms.visualizzaVenditore(username);
            return new ResponseEntity<>(venditore, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei prodotti di un venditore in GET con il suo username
     */
    @RequestMapping(value = "/venditori/{username}/prodotti", method = RequestMethod.GET)
    public ResponseEntity<Object> getVendorProducts(@PathVariable String username) {

        try {
            List<Prodotto> prodotti = ms.visualizzaProdottiVenditore(username);
            return new ResponseEntity<>(prodotti, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei pacchetti di un venditore in GET con il suo username
     */
    @RequestMapping(value = "/venditor/{username}/pacchetti", method = RequestMethod.GET)
    public ResponseEntity<Object> getVendorPackages(@PathVariable String username) {

        try {
            List<Pacchetto> pacchetti = ms.visualizzaPacchettiVenditore(username);
            return new ResponseEntity<>(pacchetti, HttpStatus.OK);
        } catch (VenditoreNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei venditori in GET
     */
    @RequestMapping(value = "/venditori", method = RequestMethod.GET)
    public ResponseEntity<Object> getVendors() {
        return new ResponseEntity<>(ms.visualizzaVenditori(), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei contenuti pubblicati
     */
    @RequestMapping(value = "/listaContenutiPubblicati", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductsPublished() {
        return new ResponseEntity<>(ms.visualizzaProdottiPerStato(Pubblicato.class), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei contenuti in via di convalida
     */
    @RequestMapping(value = "/listaContenutiInConvalida", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductsInValidation() {
        return new ResponseEntity<>(ms.visualizzaProdottiPerStato(InConvalida.class), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei contenuti ancora in stato di bozza
     */
    @RequestMapping(value = "/listaContenutiBozza", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductsDraft() {
        return new ResponseEntity<>(ms.visualizzaProdottiPerStato(Bozza.class), HttpStatus.OK);
    }

}
