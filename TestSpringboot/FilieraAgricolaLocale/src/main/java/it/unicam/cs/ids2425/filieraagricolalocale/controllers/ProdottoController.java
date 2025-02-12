package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.ProdottoBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.model.TipoPOI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;
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
   @Autowired
   ProdottoController(MarketplaceService market){
      
      MiddlewareProdotto mp = MiddlewareProdotto.link(new MiddlewareDati());
		ps = new ProdottoService(mp);
      this.ms = market;

		ps.creaProdotto(new ProdottoBuilder().setDescrizione("Prodotto bianco").setNome("Mela rossa").setData(new Date()).
		setPoi(new POI(0, 0, 0, TipoPOI.Prodotto)).setQuantita(5).setVenditore(new Venditore(null, null, null, null))
		.setPrezzo(20.0).build());

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
