package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.NonAutorizzatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import org.springframework.stereotype.Service;

@Service
public class AutorizzazioneService {

    public void controlloAutorizzazioneProdotto(Contenuto contenuto, Account venditore) {
        //Il Gestore ha sempre accesso
        if (venditore.getListaRuoli().contains(Ruolo.Gestore)) {
            return;
        }

        if (!contenuto.getVenditore().equals(venditore)) {
            throw new NonAutorizzatoException("Il venditore " + venditore.getUsername() + " non può fare operazioni su " +
                    contenuto.getNome());
        }
    }

    public void controlloAutorizzazioneAccount(Account currentAccount, Account controlledAccount) {
        if (currentAccount.getListaRuoli().contains(Ruolo.Gestore)) {
            return;
        }

        if (!currentAccount.getUsername().equals(controlledAccount.getUsername())) {
            throw new NonAutorizzatoException("L'account " + currentAccount.getUsername() +
                    " con cui si è fatto l'accesso non può operare sull'account " + controlledAccount.getUsername());
        }
    }

    public void controlloAutorizzazioneEvento(EventoAbstract evento, Account account) {
        if (account.getListaRuoli().contains(Ruolo.Gestore)) {
            return;
        }

        if (!account.getUsername().equals(evento.getAnimatore().getUsername())) {
            throw new NonAutorizzatoException("L'account " + account.getUsername() +
                    "con cui si è fatto l'accesso non può operare sull'evento di " + evento.getAnimatore().getUsername());
        }
    }

    public void controlloAutorizzazioneProposta(Visita visita, Account account) {
        if (!visita.getProposta().getVenditore().equals(account)) {
            throw new NonAutorizzatoException("Non si è autorizzati ad accettare la proposta");
        }
    }

}
