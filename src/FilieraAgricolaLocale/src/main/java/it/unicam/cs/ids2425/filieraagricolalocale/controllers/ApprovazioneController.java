package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApprovazioneController {

    //TODO GESTIONE AUTORIZZAZIONI RUOLI
    private final ApprovazioneService as;
    private final UserService us;
    private final MarketplaceService ms;
    private final AutorizzazioneService auth;

    @Autowired
    public ApprovazioneController(InitFacade i) {
        this.as = i.getaS();
        this.us = i.getuS();
        this.ms = i.getmS();
        this.auth = i.getAuthS();
    }

    /*
     * Richiedi di mandare in convalida un prodotto
     */
    @RequestMapping(value = "/richiesta/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> requestValidation(@PathVariable int id) {

        try {
            Account account = us.getCurrentUser();
            Contenuto contenuto = ms.visualizzaContenuto(id);
            auth.controlloAutorizzazioneProdotto(contenuto, account);

            as.inviaRichiesta(id);
            return new ResponseEntity<>("Richiesta spedita con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Pubblica o fa tornare a bozza un prodotto in base all'esito
     * Nel JSON va indicato solo true o false, senza parentesi graffe
     */
    @RequestMapping(value = "/convalida/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> validate(@PathVariable int id, @RequestBody boolean esito) {

        try {
            as.approva(id, esito);
            return new ResponseEntity<>("Risultato convalidazione eseguito con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
