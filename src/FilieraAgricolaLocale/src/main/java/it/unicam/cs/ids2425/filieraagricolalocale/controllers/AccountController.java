package it.unicam.cs.ids2425.filieraagricolalocale.controllers;


import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.ElementoOrdineDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.UtenteDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.VenditoreDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.AutorizzazioneService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;

@RestController
@RequestMapping("/account")
public class AccountController {
   
   private UserService uService;
   private AutorizzazioneService auth;
   private MarketplaceService mService;

   @Autowired
   AccountController(InitFacade i){
      this.uService = i.getuS();
      this.auth = i.getAuthS();
      this.mService = i.getmS();
   }

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/creaUtente", method = RequestMethod.POST)
      public ResponseEntity<Object> creaUtente(@RequestBody UtenteDTO u) {

      Utente us = new Utente(u.getNome(), u.getCognome(), u.getDataDiNascita(), u.getUsername(), u.getPassword(), u.getListaRuoli());
      uService.creaUtente(us);
      return new ResponseEntity<>("Utente is created successfully", HttpStatus.CREATED);
	}

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/creaVenditore", method = RequestMethod.POST)
      public ResponseEntity<Object> creaVenditore(@RequestBody VenditoreDTO u) {

      Venditore vu = new Venditore(u.getRagioneSociale(), u.getPIVA(), u.getUsername(), u.getPassword(), u.getListaRuoli(), u.getDescrizione(), u.getLocalizzazione());
      uService.creaVenditore(vu);
      return new ResponseEntity<>("Venditore is created successfully", HttpStatus.CREATED);
   }

   /*
    * Elimina profilo utente
    */
	@RequestMapping(value = "/eliminaUtente/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> rimuoviUtente(@PathVariable("id") String id) {
        Account currentAccount = uService.getCurrentUser();
        Account controlledAccount = uService.getAccount(id);
        auth.controlloAutorizzazioneAccount(currentAccount, controlledAccount);

        uService.rimuoviUtente(id);
        return new ResponseEntity<>("Utente eliminato con successo", HttpStatus.OK);
	}
	
   /*
    * Elimina profilo venditore
    */
	@RequestMapping(value = "/eliminaVenditore/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> rimuoviVenditore(@PathVariable("id") String id) {

        Account currentAccount = uService.getCurrentUser();
        Account controlledAccount = uService.getAccount(id);
        auth.controlloAutorizzazioneAccount(currentAccount, controlledAccount);

        uService.rimuoviVenditore(id);
        return new ResponseEntity<>("Venditore eliminato con successo", HttpStatus.OK);
   }

   /*
    * Modifica profilo utente
    */
   @RequestMapping(value = "/modificaUtente/{id}", method = RequestMethod.PUT)
      public ResponseEntity<Object> modificaUtente(@PathVariable("id") String id, @RequestBody UtenteDTO u) {

       Account currentAccount = uService.getCurrentUser();
       Account controlledAccount = uService.getAccount(id);
       auth.controlloAutorizzazioneAccount(currentAccount, controlledAccount);

      Utente us = new Utente(u.getNome(), u.getCognome(), u.getDataDiNascita(), u.getUsername(), u.getPassword(), u.getListaRuoli());
      uService.modificaUtente(id, us);
      return new ResponseEntity<>("Utente modificato con successo", HttpStatus.OK);
	}

   /*
    * Modifica profilo venditore
    */
   @RequestMapping(value = "/modificaVenditore/{id}", method = RequestMethod.PUT)
      public ResponseEntity<Object> modificaVenditore(@PathVariable("id") String id, @RequestBody VenditoreDTO u) {

       Account currentAccount = uService.getCurrentUser();
       Account controlledAccount = uService.getAccount(id);
       auth.controlloAutorizzazioneAccount(currentAccount, controlledAccount);

      Venditore vu = new Venditore(u.getRagioneSociale(), u.getPIVA(), u.getUsername(), u.getPassword(), u.getListaRuoli(), u.getDescrizione(), u.getLocalizzazione());
      uService.modificaVenditore(id, vu);
      return new ResponseEntity<>("Venditore modificato con successo", HttpStatus.OK);
   }

   @RequestMapping(value = "/listaUtenti")
   public ResponseEntity<Object> getUtenti() {
      return new ResponseEntity<>(uService.getElencoUtenti(), HttpStatus.OK);
   }

   @RequestMapping(value = "/listaVenditori")
   public ResponseEntity<Object> getProducts() {
      return new ResponseEntity<>(uService.getElencoVenditore(), HttpStatus.OK);
   }

   @GetMapping("/ricercaUtente/{id}")
	public ResponseEntity<Object> getUtente(@PathVariable("id") String id) {
	   return new ResponseEntity<>(uService.getUtente(id), HttpStatus.OK);
	}

   @GetMapping("/ricercaVenditore/{id}")
   public ResponseEntity<Object> getVenditore(@PathVariable("id") String id) {
      return new ResponseEntity<>(uService.getVenditore(id), HttpStatus.OK);
   }

   /*
    * Modifica ruoli utente
    */
   @RequestMapping(value = "/modificaRuoliUtente/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> modificaRuoliUtente(@PathVariable("id") String id, @RequestBody List<Ruolo> u) {
      uService.modificaRuoliUtente(u, id);
      return new ResponseEntity<>("Ruoli Utente modificati con successo", HttpStatus.OK);
   }

   /*
    * Modifica ruoli venditore
    */
    @RequestMapping(value = "/modificaRuoliVenditore/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> modificaRuoliVenditore(@PathVariable("id") String id, @RequestBody List<Ruolo> u) {
      uService.modificaRuoliVenditore(u, id);
      return new ResponseEntity<>("Ruoli venditore modificati con successo", HttpStatus.OK);
   }

   /*
   * Aggiunge un contenuto al carrello passando i valori in POST
   */
   @RequestMapping(value = "/carrello/aggiungiContenuto", method = RequestMethod.POST)
   public ResponseEntity<Object> addContentToCart(@RequestBody ElementoOrdineDTO d) {
        
      Account account = uService.getCurrentUser();
      if(account instanceof Venditore) throw new NonAutorizzatoException();
      try {
         uService.aggiungiContenutoCarrello(account.getUsername(), mService.visualizzaContenuto(d.getId()), d.getQuantita());
         return new ResponseEntity<>("Elemento aggiunto con successo", HttpStatus.CREATED);
      } catch (DatiIncorrettiException e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
      } catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
	}

   /*
   * Ottiene il carrello in GET con il suo id
   */
   @RequestMapping(value = "/carrello/{id}", method = RequestMethod.GET)
   public ResponseEntity<Object> getCart(@PathVariable String id) {

      try {
         Carrello c = uService.getCarrelloUtente(id);
         return new ResponseEntity<>(c, HttpStatus.OK);
      } catch (DatiIncorrettiException e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
      } catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   /*
   * Cancella un contenuto dal carrello passandone l'id
   */
   @RequestMapping(value = "/carrello/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> removeContentFromCart(@RequestBody @PathVariable int id) {

      try {
         Account account = uService.getCurrentUser();
         if(account instanceof Venditore) throw new NonAutorizzatoException();

         uService.rimuoviContenutoCarrello(account.getUsername(), mService.visualizzaContenuto(id));
         return new ResponseEntity<>("Contenuto rimosso dal carrello con successo.", HttpStatus.OK);
      } catch (ProdottoNonTrovatoException e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
      } catch (NonAutorizzatoException e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
      } catch (Exception e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   /*
   * Aggiunge un contenuto al carrello passando i valori in POST
   */
   @RequestMapping(value = "/carrello/modificaQuantitaContenuto", method = RequestMethod.PUT)
   public ResponseEntity<Object> editQuantityCart(@RequestBody ElementoOrdineDTO d) {
         
      Account account = uService.getCurrentUser();
      if(account instanceof Venditore) throw new NonAutorizzatoException();

      uService.modificaQuantitaCarrello(account.getUsername(), mService.visualizzaContenuto(d.getId()), d.getQuantita());

      return new ResponseEntity<>("Elemento aggiunto con successo", HttpStatus.CREATED);
   }



}
