package it.unicam.cs.ids2425.filieraagricolalocale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public enum RuoloUtente implements Ruolo{
    Curatore,
    Animatore,
    Gestore;
}
