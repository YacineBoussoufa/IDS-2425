package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
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

   @Autowired
   AccountController(InitFacade i){
      this.uService = i.getuS();

      List<RuoloUtente> l = new LinkedList<>();
      l.add(RuoloUtente.Gestore);
      l.add(RuoloUtente.Curatore);
      l.add(RuoloUtente.Animatore);
      this.uService.creaUtente(new Utente("Michele", "Antiqus", Date.from(Instant.now()), "micky", "ciao", l));
      List<RuoloVenditore> lv = new LinkedList<>();
      lv.add(RuoloVenditore.Produttore);
      this.uService.creaVenditore("La Tana", "123", "key", "123", lv, "grossa fabbrica",
              new POI(0, 0, 0, TipoPOI.Azienda));
   }

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/creaUtente", method = RequestMethod.POST)
      public ResponseEntity<Object> creaUtente(@RequestBody Utente u) {
      uService.creaUtente(u);
      return new ResponseEntity<>("Utente is created successfully", HttpStatus.CREATED);
	}

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/creaVenditore", method = RequestMethod.POST)
      public ResponseEntity<Object> creaVenditore(@RequestBody Venditore u) {
      uService.creaVenditore(u);
      return new ResponseEntity<>("Venditore is created successfully", HttpStatus.CREATED);
   }

   /*
    * Elimina profilo utente
    */
	@RequestMapping(value = "/eliminaUtente/{id}", method = RequestMethod.DELETE)
      public ResponseEntity<Object> rimuoviUtente(@PathVariable("id") String id) {
         uService.rimuoviUtente(id);
      return new ResponseEntity<>("Utente eliminato con successo", HttpStatus.OK);
	}
	
   /*
    * Elimina profilo venditore
    */
	@RequestMapping(value = "/eliminaVenditore/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> rimuoviVenditore(@PathVariable("id") String id) {
      uService.rimuoviVenditore(id);
      return new ResponseEntity<>("Venditore eliminato con successo", HttpStatus.OK);
   }

   /*
    * Modifica profilo utente
    */
   @RequestMapping(value = "/modificaUtente/{id}", method = RequestMethod.PUT)
      public ResponseEntity<Object> modificaUtente(@PathVariable("id") String id, @RequestBody Utente u) {
      uService.modificaUtente(id, u);
      return new ResponseEntity<>("Utente modificato con successo", HttpStatus.OK);
	}

   /*
    * Modifica profilo venditore
    */
   @RequestMapping(value = "/modificaVenditore/{id}", method = RequestMethod.PUT)
      public ResponseEntity<Object> modificaVenditore(@PathVariable("id") String id, @RequestBody Venditore u) {
      uService.modificaVenditore(id, u);
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
   public ResponseEntity<Object> modificaRuoliUtente(@PathVariable("id") String id, @RequestBody List<RuoloUtente> u) {
      uService.modificaRuoliUtente(u, id);
      return new ResponseEntity<>("Ruoli Utente modificati con successo", HttpStatus.OK);
   }

   /*
    * Modifica ruoli venditore
    */
    @RequestMapping(value = "/modificaRuoliVenditore/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> modificaRuoliVenditore(@PathVariable("id") String id, @RequestBody List<RuoloVenditore> u) {
      uService.modificaRuoliVenditore(u, id);
      return new ResponseEntity<>("Ruoli venditore modificati con successo", HttpStatus.OK);
   }



}
