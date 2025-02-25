package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;

@ControllerAdvice
public class ExceptionController {

   @ExceptionHandler(value = DatiIncorrettiException.class)
   public ResponseEntity<Object> exception(DatiIncorrettiException exception) {
       return new ResponseEntity<>("Errore presente nei dati inviati", HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(value = NonAutorizzatoException.class)
   public ResponseEntity<Object> exception(NonAutorizzatoException exception) {
       return new ResponseEntity<>("Utente non autorizzato", HttpStatus.FORBIDDEN);
   }
}
