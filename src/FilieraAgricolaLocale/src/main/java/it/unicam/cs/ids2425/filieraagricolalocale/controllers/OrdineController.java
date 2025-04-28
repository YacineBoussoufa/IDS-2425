package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.OrdineDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ruolo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.services.OrdineService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

   private OrdineService oService;
   private UserService uService;

   @Autowired
   OrdineController(InitFacade i){
      this.oService = i.getoS();
      this.uService = i.getuS();
   }

   /*
    * Crea un nuovo ordine passando i valori in POST
    */
   @RequestMapping(value = "/creaOrdine", method = RequestMethod.POST)
   public ResponseEntity<Object> creaOrdine(@RequestBody OrdineDTO d) {
      
      // recupera l'utente dalla repo
      Utente u = uService.getUtente(d.getU());

      oService.creaOrdine(d.getD(), u, d.getI(), d.getM());
      uService.svuotaCarrello(u.getUsername());
      return new ResponseEntity<>("Ordine creato con successo", HttpStatus.CREATED);
	}

   /*
    * Modifica data consegna
    */
   @RequestMapping(value = "/modificaDataConsegna/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> modificaDataConsegna(@PathVariable("id") int id, @RequestBody Date d) {
      oService.modificaDataDiConsegna(id, d);
      return new ResponseEntity<>("Data consegna modificata con successo", HttpStatus.OK);
	}

   /*
    * Modifica data consegna
    */
   @RequestMapping(value = "/modificaIndirizzo/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> modificaIndirizzo(@PathVariable("id") int id, @RequestBody Indirizzo d) {
      if(!uService.getCurrentUser().getListaRuoli().contains(Ruolo.Gestore)) {
         if(!uService.getCurrentUser().getUsername().equals(oService.getOrdine(id).getUser().getUsername()))
            throw new NonAutorizzatoException();
      }
      oService.modificaIndirizzo(id, d);
      return new ResponseEntity<>("Indirizzo modificato con successo", HttpStatus.OK);
   }

   @GetMapping("/ricercaOrdine/{id}")
	public ResponseEntity<Object> getOrdine(@PathVariable("id") int id) {
	   return new ResponseEntity<>(oService.getOrdine(id), HttpStatus.OK);
	}

   @GetMapping("/ricercaOrdineUtente/{id}")
	public ResponseEntity<Object> getOrdineUtente(@PathVariable("id") String id) {
	   return new ResponseEntity<>(oService.getOrdiniUtente(id), HttpStatus.OK);
	}

   @GetMapping("/ricercaOrdineVenditore/{id}")
	public ResponseEntity<Object> getOrdineVenditore(@PathVariable("id") String id) {
	   return new ResponseEntity<>(oService.getOrdiniVenditore(id), HttpStatus.OK);
	}

}
