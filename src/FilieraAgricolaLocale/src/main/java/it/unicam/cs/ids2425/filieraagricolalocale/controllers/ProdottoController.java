package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;

@RestController
@RequestMapping(value = "/gestioneContenuti")
public class ProdottoController {
   
   private final ProdottoService ps;

   //TODO gestire autorizzazioni
    ProdottoController(InitFacade i){
       ps = i.getpS();
    }    

   /*
    * Crea un nuovo contenuto passando i valori in POST
    */
   @RequestMapping(value = "/contenuto/crea", method = RequestMethod.POST)
   public ResponseEntity<Object> createContent(@RequestBody Contenuto contenuto) {

       try {
           ps.creaContenuto(contenuto);
           return new ResponseEntity<>("Contenuto creato con successo.", HttpStatus.CREATED);
       } catch (DatiIncorrettiException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }

    /*
     * Modifica un contenuto passando soltanto i dati da modificare in POST
     */
    @RequestMapping(value = "/contenuto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editContent(@PathVariable int id, @RequestBody Contenuto contenuto) {

        try {
            ps.modificaContenuto(id, contenuto);
            return new ResponseEntity<>("Contenuto modificato con successo.", HttpStatus.OK);
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
    @RequestMapping(value = "/contenuto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteContent(@RequestBody @PathVariable int id) {

        try {
            ps.eliminaContenuto(id);
            return new ResponseEntity<>("Contenuto eliminato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Aggiunge quantità ad un contenuto
     */
    @RequestMapping(value = "/restock/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> restock(@PathVariable int id, @RequestBody int quantita) {

        try {
            ps.restock(id, quantita);
            return new ResponseEntity<>("Contenuto restock con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
