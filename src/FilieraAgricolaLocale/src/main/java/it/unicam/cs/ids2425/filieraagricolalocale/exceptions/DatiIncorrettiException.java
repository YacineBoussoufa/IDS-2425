package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class DatiIncorrettiException extends RuntimeException {

    String message = "";

    public DatiIncorrettiException() {

    }

    public DatiIncorrettiException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
