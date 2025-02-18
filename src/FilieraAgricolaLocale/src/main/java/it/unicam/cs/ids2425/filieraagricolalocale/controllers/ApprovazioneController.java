package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.services.ApprovazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestioneConvalide")
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
    @RequestMapping(value = "/prodotto/richiesta", method = RequestMethod.PUT)
    public ResponseEntity<Object> requestProductValidation(@RequestBody int id) {

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
    @RequestMapping(value = "/pacchetto/richiesta", method = RequestMethod.PUT)
    public ResponseEntity<Object> requestPackageValidation(@RequestBody int id) {

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
     *
     */
    @RequestMapping(value = "/prodotto/convalida", method = RequestMethod.PUT)
    public ResponseEntity<Object> validateProduct(@RequestBody int id, boolean esito) {

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
     *
     */
    @RequestMapping(value = "/pacchetto/convalida", method = RequestMethod.PUT)
    public ResponseEntity<Object> validatePackage(@RequestBody int id, boolean esito) {

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
