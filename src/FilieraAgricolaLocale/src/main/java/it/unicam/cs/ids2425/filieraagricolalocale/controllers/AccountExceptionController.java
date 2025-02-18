package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;

@ControllerAdvice
public class AccountExceptionController {

   @ExceptionHandler(value = DatiIncorrettiException.class)
   public ResponseEntity<Object> exception(DatiIncorrettiException exception) {
       return new ResponseEntity<>("Account non trovatob", HttpStatus.NOT_FOUND);
   }
}
