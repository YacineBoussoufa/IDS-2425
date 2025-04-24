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

    private final MarketplaceService ms;

    @Autowired
    public MarketplaceController(InitFacade i) {
        this.ms = i.getmS();
    }

    /*
     * Ottiene un prodotto in GET con il suo id
     */
    @RequestMapping(value = "/contenuti/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable int id) {

        try {
            Contenuto contenuto = ms.visualizzaContenuto(id);
            return new ResponseEntity<>(contenuto, HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Ottieni la lista dei contenuti in GET
     */
    @RequestMapping(value = "/contenuti", method = RequestMethod.GET)
    public ResponseEntity<Object> getContents() {
        return new ResponseEntity<>(ms.visualizzaContenuti(), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei prodotti in GET
     */
    @RequestMapping(value = "/contenuti/prodotti", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(ms.visualizzaProdotti(), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei pacchetti in GET
     */
    @RequestMapping(value = "/contenuti/pacchetti", method = RequestMethod.GET)
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
     * Ottieni la lista dei contenuti di un venditore in GET con il suo username
     */
    @RequestMapping(value = "/venditori/{username}/contenuti", method = RequestMethod.GET)
    public ResponseEntity<Object> getVendorProducts(@PathVariable String username) {

        try {
            List<Contenuto> prodotti = ms.visualizzaContenutiVenditore(username);
            return new ResponseEntity<>(prodotti, HttpStatus.OK);
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
