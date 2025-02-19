package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.services.ApprovazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApprovazioneController {

    //TODO GESTIONE AUTORIZZAZIONI RUOLI
    private ApprovazioneService as;

    @Autowired
    public ApprovazioneController(ApprovazioneService as) {
        this.as = as;
    }

    /*
     * Richiedi di mandare in convalida un prodotto
     */
    @RequestMapping(value = "/richiesta/prodotto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> requestProductValidation(@PathVariable int id) {

        try {
            as.inviaRichiestaProdotto(id);
            return new ResponseEntity<>("Richiesta spedita con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Richiedi di mandare in convalida un pacchetto
     */
    @RequestMapping(value = "/richiesta/pacchetto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> requestPackageValidation(@PathVariable int id) {

        try {
            as.inviaRichiestaPacchetto(id);
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
    @RequestMapping(value = "/convalida/prodotto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> validateProduct(@PathVariable int id, @RequestBody boolean esito) {

        try {
            as.approvaProdotto(id, esito);
            return new ResponseEntity<>("Risultato convalidazione eseguito con successo.", HttpStatus.OK);
        } catch (ProdottoNonTrovatoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DatiIncorrettiException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * Pubblica o fa tornare a bozza un pacchetto in base all'esito
     * Nel JSON va indicato solo true o false, senza parentesi graffe
     */
    @RequestMapping(value = "/convalida/pacchetto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> validatePackage(@PathVariable int id, @RequestBody boolean esito) {

        try {
            as.approvaPacchetto(id, esito);
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
