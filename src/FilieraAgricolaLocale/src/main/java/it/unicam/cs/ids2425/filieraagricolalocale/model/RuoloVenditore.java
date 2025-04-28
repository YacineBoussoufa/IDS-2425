package it.unicam.cs.ids2425.filieraagricolalocale.model;

public enum RuoloVenditore {
    Produttore("Produttore"),
    Trasformatore("Trasformatore"),
    Distributore("Distributore");

    private final String message;

    RuoloVenditore(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
