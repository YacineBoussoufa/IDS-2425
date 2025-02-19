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
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/prodotto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editProduct(@PathVariable int id, @RequestBody Prodotto prodotto) {

        try {
            ps.modificaProdotto(id, prodotto);
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
    @RequestMapping(value = "/pacchetto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editPackage(@PathVariable int id, @RequestBody Pacchetto pacchetto) {

        try {
            ps.modificaPacchetto(id, pacchetto);
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
    @RequestMapping(value = "/prodotto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@RequestBody @PathVariable int id) {

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
    @RequestMapping(value = "/pacchetto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePackage(@PathVariable int id) {

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
    @RequestMapping(value = "/restock/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> restock(@PathVariable int id, @RequestBody int quantita) {

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
