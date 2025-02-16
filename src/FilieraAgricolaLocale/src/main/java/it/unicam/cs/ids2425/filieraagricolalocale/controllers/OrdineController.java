package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.OrdineService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareIndirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewarePagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareQuantita;

public class OrdineController {

   private OrdineService oService;
   private MarketplaceService mService;

   OrdineController(){
      // TODO fixare questa responsabilita'
      this.mService = new MarketplaceService();
      MiddlewareOrdine m = MiddlewareOrdine.link(new MiddlewareIndirizzo(), new MiddlewarePagamento(), new MiddlewareQuantita(mService));
      this.oService = new OrdineService(m);
   }

   /*
    * Crea un nuovo ordine passando i valori in POST
    */
   @RequestMapping(value = "/creaOrdine", method = RequestMethod.POST)
   public ResponseEntity<Object> creaOrdine(@RequestBody Date d, List<ElementoOrdineDTO> elements, Account u, Indirizzo i, Pagamento m) {
      
      Map<Contenuto, Integer> mappa = new HashMap<>();
      for (ElementoOrdineDTO elementoOrdineDTO : elements) {
         mappa.put(elementoOrdineDTO.getP(), elementoOrdineDTO.getQuantita());
      }
      oService.creaOrdine(d, mappa, u, i, m);;
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
