package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class VenditoreNonTrovatoException extends RuntimeException{
    String message = "";

    public VenditoreNonTrovatoException() {

    }

    public VenditoreNonTrovatoException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
