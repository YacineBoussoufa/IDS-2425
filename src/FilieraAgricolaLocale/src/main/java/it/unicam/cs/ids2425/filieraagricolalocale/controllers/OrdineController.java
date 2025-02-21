package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.ElementoOrdineDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO.OrdineDTO;
import it.unicam.cs.ids2425.filieraagricolalocale.controllers.util.ContenutoDeserializer;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.OrdineService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.ProdottoService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareIndirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewarePagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewarePubblicato;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareQuantita;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

   private OrdineService oService;
   private MarketplaceService mService;
   private ProdottoService pService;
   
   @Autowired
   OrdineController(OrdineService oS, MarketplaceService mS, ProdottoService pS){
      // TODO fixare questa responsabilita'
      this.mService = mS;
      this.oService = oS;
      this.pService = pS;
      MiddlewareOrdine m = MiddlewareOrdine.link(new MiddlewareIndirizzo(), new MiddlewarePagamento(), 
      new MiddlewareQuantita(mService, pService), new MiddlewarePubblicato(mService));
      this.oService.setMiddleware(m);

   }

   /*
    * Crea un nuovo ordine passando i valori in POST
    */
   @RequestMapping(value = "/creaOrdine", method = RequestMethod.POST)
   public ResponseEntity<Object> creaOrdine(@RequestBody OrdineDTO d) {
      
      Map<Contenuto, Integer> mappa = new HashMap<>();
      for (ElementoOrdineDTO elementoOrdineDTO : d.getLinee()) {
         elementoOrdineDTO.getP().setId(elementoOrdineDTO.getId());
         System.out.println(elementoOrdineDTO.getP().getId());
         mappa.put(elementoOrdineDTO.getP(), elementoOrdineDTO.getQuantita());
      }
      oService.creaOrdine(d.getD(), mappa, d.getU(), d.getI(), d.getM());;
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
