package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class EventoNonTrovatoException extends RuntimeException {
    public EventoNonTrovatoException() {}

    public EventoNonTrovatoException(String message) {
        super(message);
    }
}
