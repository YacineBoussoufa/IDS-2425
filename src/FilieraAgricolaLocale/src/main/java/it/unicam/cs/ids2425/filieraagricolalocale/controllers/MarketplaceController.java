package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Pubblicato;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketplace")
public class MarketplaceController {

    private MarketplaceService ms;

    @Autowired
    public MarketplaceController(MarketplaceService ms) {
        this.ms = ms;
    }

    /*
     * Ottieni la lista dei prodotti in GET
     */
    @RequestMapping(value = "/listaProdotti")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(ms.visualizzaProdotti(), HttpStatus.OK);
    }

    /*
     * Ottieni la lista dei prodotti pubblicati
     */
    @RequestMapping(value = "/listaProdottiPubblicati")
    public ResponseEntity<Object> getProductsConvalidati() {
        return new ResponseEntity<>(ms.visualizzaProdottiPerStato(Pubblicato.class), HttpStatus.OK);
    }

}
