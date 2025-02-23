package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class NumeroMassimoUtentiException extends RuntimeException{
    String message = "";

    public NumeroMassimoUtentiException() {

    }

    public NumeroMassimoUtentiException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
