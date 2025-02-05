package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class ProdottoNonTrovatoException extends RuntimeException {

    String message = "";

    public ProdottoNonTrovatoException() {

    }

    public ProdottoNonTrovatoException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
