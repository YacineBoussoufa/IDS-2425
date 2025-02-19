package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareDati;

@RestController
@RequestMapping(value = "/gestioneContenuti")
public class ProdottoController {
   
   private ProdottoService ps;

   //todo gestione diversa di autowired (?)
   //TODO gestire autorizzazioni per la creazione
   ProdottoController(){
      
      ps = new ProdottoService();
      MiddlewareProdotto mp = MiddlewareProdotto.link(new MiddlewareDati());
      ps.setMiddleware(mp);
      
      List<RuoloVenditore> l = new LinkedList<>();
      l.add(RuoloVenditore.Distributore);

      ps.creaProdotto(new ProdottoBuilder().setDescrizione("Prodotto bianco").setNome("Mela rossa").setData(Date.from(Instant.now())).
        setPoi(new POI(0, 0, 0, TipoPOI.Prodotto)).setQuantita(5).setVenditore(new Venditore(null, null, null,
                        null, l, null, null))
        .setPrezzo(20.0).build());
        

   }

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/prodotto", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Prodotto prodotto) {

       try {
           ps.creaProdotto(prodotto);
           return new ResponseEntity<>("Prodotto creato con successo.", HttpStatus.CREATED);
       } catch (DatiIncorrettiException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }

   /*
    * Crea un nuovo pacchetto passando i suoi prodotti in POST
    */
    @RequestMapping(value = "/pacchetto", method = RequestMethod.POST)
    public ResponseEntity<Object> createPackage(@RequestBody Pacchetto pacchetto) {

        try {
            ps.creaPacchetto(pacchetto);
            return new ResponseEntity<>("Pacchetto creato con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Modifica un prodotto passando soltanto i dati da modificare in POST
     */
    @RequestMapping(value = "/prodotto", method = RequestMethod.PUT)
    public ResponseEntity<Object> editProduct(@RequestBody Prodotto prodotto) {

        try {
            ps.modificaProdotto(prodotto.getId(), prodotto);
            return new ResponseEntity<>("Prodotto modificato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Modifica un pacchetto passandone uno nuovo
     */
    @RequestMapping(value = "/pacchetto", method = RequestMethod.PUT)
    public ResponseEntity<Object> editPackage(@RequestBody Pacchetto pacchetto) {

        try {
            ps.modificaPacchetto(pacchetto.getId(), pacchetto);
            return new ResponseEntity<>("Pacchetto modificato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Cancella un prodotto passandone l'id
     */
    @RequestMapping(value = "/prodotto", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@RequestBody int id) {

        try {
            ps.eliminaProdotto(id);
            return new ResponseEntity<>("Prodotto eliminato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Cancella un pacchetto passandone l'id
     */
    @RequestMapping(value = "/pacchetto", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePackage(@RequestBody int id) {

        try {
            ps.eliminaPacchetto(id);
            return new ResponseEntity<>("Pacchetto eliminato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Aggiunge quantità ad un prodotto
     */
    @RequestMapping(value = "/restock", method = RequestMethod.PUT)
    public ResponseEntity<Object> restock(@RequestBody int id, int quantita) {

        try {
            ps.restock(id, quantita);
            return new ResponseEntity<>("Prodotto restock con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
