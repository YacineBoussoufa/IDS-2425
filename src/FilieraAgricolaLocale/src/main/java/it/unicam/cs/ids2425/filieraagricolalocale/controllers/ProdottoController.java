package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.ContenutoMapper;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.PacchettoDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.ProdottoDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import it.unicam.cs.ids2425.filieraagricolalocale.services.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;

@RestController
@RequestMapping(value = "/gestioneContenuti")
public class ProdottoController {
   
   private final ProdottoService ps;
   private final UserService us;
   private final AutorizzazioneService auth;
   private final MarketplaceService ms;

    ProdottoController(InitFacade i){
       ps = i.getpS();
       us = i.getuS();
       auth = i.getAuthS();
       ms = i.getmS();
    }    

   /*
    * Crea un nuovo contenuto passando i valori in POST
    */
   @RequestMapping(value = "/contenuto/creaProdotto", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody ProdottoDTO contenutodto) {

       try {
           Contenuto contenuto = ContenutoMapper.ToProdotto(contenutodto, ms, us);
           Account account = us.getCurrentUser();
           auth.controlloAutorizzazioneProdotto(contenuto, account);

           ps.creaContenuto(contenuto);
           return new ResponseEntity<>("Contenuto creato con successo.", HttpStatus.CREATED);
       } catch (DatiIncorrettiException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       } catch (NonAutorizzatoException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }

   /*
    * Crea un nuovo contenuto passando i valori in POST
    */
    @RequestMapping(value = "/contenuto/creaPacchetto", method = RequestMethod.POST)
    public ResponseEntity<Object> createPacket(@RequestBody PacchettoDTO contenutodto) {
 
        try {
            Contenuto contenuto = ContenutoMapper.ToPacchetto(contenutodto, ms, us);
            Account account = us.getCurrentUser();
            auth.controlloAutorizzazioneProdotto(contenuto, account);
 
            ps.creaContenuto(contenuto);
            return new ResponseEntity<>("Contenuto creato con successo.", HttpStatus.CREATED);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
 
    }

    /*
     * Modifica un contenuto passando soltanto i dati da modificare in POST
     */
    @RequestMapping(value = "/contenuto/modificaProdotto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editProduct(@PathVariable int id, @RequestBody ProdottoDTO contenutodto) {

        try {
            Contenuto contenuto = ContenutoMapper.ToProdotto(contenutodto, ms, us);
            Account account = us.getCurrentUser();
            Contenuto contenutoAttuale = ms.visualizzaContenuto(id);
            contenuto.setId(id);
            System.out.println(contenuto.getId());
            auth.controlloAutorizzazioneProdotto(contenutoAttuale, account);

            ps.modificaContenuto(id, contenuto);
            return new ResponseEntity<>("Contenuto modificato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Modifica un contenuto passando soltanto i dati da modificare in POST
     */
    @RequestMapping(value = "/contenuto/modificaPacchetto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editPacket(@PathVariable int id, @RequestBody PacchettoDTO contenutodto) {

        try {
            Contenuto contenuto = ContenutoMapper.ToPacchetto(contenutodto, ms, us);
            Account account = us.getCurrentUser();
            Contenuto contenutoAttuale = ms.visualizzaContenuto(id);
            contenuto.setId(id);
            System.out.println(contenuto.getId());
            auth.controlloAutorizzazioneProdotto(contenutoAttuale, account);

            ps.modificaContenuto(id, contenuto);
            return new ResponseEntity<>("Contenuto modificato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
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
            Account account = us.getCurrentUser();
            Contenuto contenuto = ms.visualizzaContenuto(id);
            auth.controlloAutorizzazioneProdotto(contenuto, account);

            ps.eliminaContenuto(id);
            return new ResponseEntity<>("Contenuto eliminato con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Restituisce un link per la condivisione social
     */
    @RequestMapping(value = "/contenuto/{id}/condividi", method = RequestMethod.GET)
    public ResponseEntity<Object> getContent(@PathVariable int id, @RequestBody String site) {

        try {
            return new ResponseEntity<>(ps.generaLinkSocial(site, id), HttpStatus.OK);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
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
            Account account = us.getCurrentUser();
            Contenuto contenuto = ms.visualizzaContenuto(id);
            auth.controlloAutorizzazioneProdotto(contenuto, account);

            ps.restock(id, quantita);
            return new ResponseEntity<>("Contenuto restock con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NonAutorizzatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
