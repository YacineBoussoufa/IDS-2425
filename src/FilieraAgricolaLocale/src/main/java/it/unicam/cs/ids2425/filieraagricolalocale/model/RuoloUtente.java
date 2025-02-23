package it.unicam.cs.ids2425.filieraagricolalocale.model;

public enum RuoloUtente implements Ruolo{
    Curatore("Curatore"),
    Animatore("Animatore"),
    Gestore("Gestore");

    private final String message;

    RuoloUtente(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
