package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareDati;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
   
   private ProdottoService ps;
   private MarketplaceService ms;

   //TODO
   // SINGLE RESPONBILITY PROBLEM
   ProdottoController(){
      
      MiddlewareProdotto mp = MiddlewareProdotto.link(new MiddlewareDati());
		ps = new ProdottoService(mp);
      ms = new MarketplaceService();

   }

   /*
    * Crea un nuovo prodotto passando i valori in POST
    */
   @RequestMapping(value = "/crea", method = RequestMethod.POST)
      public ResponseEntity<Object> createProduct(@RequestBody Prodotto prodotto) {
      ps.creaProdotto(prodotto);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

   /*
    * Ottieni la lista dei prodotti in GET
    */
   @RequestMapping(value = "/lista")
      public ResponseEntity<Object> getProducts() {
      return new ResponseEntity<>(ms.visualizzaProdotti(), HttpStatus.OK);
   }


}
