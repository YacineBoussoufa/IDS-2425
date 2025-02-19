package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;

import java.util.HashMap;
import java.util.Map;

public class ProdottoService {

    //todo MOCK DI UNA TABELLA PRODOTTI
    public static Map<Integer, Prodotto> repoProdotti = new HashMap<>();
    public static Map<Integer, Pacchetto> repoPacchetti = new HashMap<>();
    //todo correttezza venditore
    public static Map<String, Venditore> repoVenditori = new HashMap<>();
    public static int idProdottoCounter = 0;
    public static int idPacchettoCounter = 0;

    private MiddlewareProdotto middlewareHead;

    public void setMiddleware(MiddlewareProdotto m){
      this.middlewareHead = m;
    }

    /**
     * Crea un prodotto e controlla i dati inseriti.
     *
     * @param prodotto Prodotto da aggiungere
     *
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    public void creaProdotto(Prodotto prodotto) {
        int id = idProdottoCounter++;
        //todo id con database

        //controllo dati
        if (middlewareHead.check(prodotto)) {
            repoProdotti.put(id, prodotto);
        } else {
            throw new DatiIncorrettiException("I dati inseriti non sono accettabili.");
        }

    }

    /**
     * Crea un pacchetto e controlla i dati dei singoli prodotti che lo formano
     *
     * @param pacchetto Pacchetto
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    public void creaPacchetto(Pacchetto pacchetto) {
        int id = idPacchettoCounter++;

        for (Prodotto prodotto : pacchetto.getListaProdotti()) {
            if (!middlewareHead.check(prodotto)) {
                throw new DatiIncorrettiException("I dati inseriti non sono accettabili.");
            }
        }

        repoPacchetti.put(id, pacchetto);
    }

    /**
     * Modifica un prodotto a partire da un builder.
     * Setterà tutti i campi non vuoti con i campi inseriti dentro il builder.
     * Modificherà poi stato del prodotto in Bozza affinchè si proceda alla convalida delle modifiche.
     *
     * @param id ID del prodotto da modificare
     * @param modifiche Prodotto che contiene solo i campi da modificare
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    public void modificaProdotto(int id, Prodotto modifiche) {
        if (!repoProdotti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste prodotto con id " + id);
        Prodotto prodottoEsistente = repoProdotti.get(id);
        ProdottoBuilder attuale = ProdottoBuilder.copiaDa(prodottoEsistente);

        //tutti gli elementi non vuoti sono modificati
        attuale.setNome(modifiche.getNome() != null ? modifiche.getNome() : attuale.getNome());
        attuale.setDescrizione(modifiche.getDescrizione() != null ? modifiche.getDescrizione() : attuale.getDescrizione());
        attuale.setPrezzo(modifiche.getPrezzo() != 0 ? modifiche.getPrezzo() : attuale.getPrezzo());
        attuale.setQuantita(modifiche.getQuantita() != 0 ? modifiche.getQuantita() : attuale.getQuantita());
        attuale.setData(modifiche.getData() != null ? modifiche.getData() : attuale.getData());
        attuale.setPoi(modifiche.getPoi() != null ? modifiche.getPoi() : attuale.getPoi());
        attuale.setListaEtichette(!modifiche.getListaEtichette().isEmpty() ? modifiche.getListaEtichette()
                : attuale.getListaEtichette());
        attuale.setIngredienti(!modifiche.getIngredienti().isEmpty() ? modifiche.getIngredienti() : attuale.getIngredienti());

        //viene creato un nuovo prodotto da mettere allo stesso id; avrà stato Bozza
        Prodotto prodottoModificato = attuale.build();

        //TODO dopo la modifica lo stato del prodotto dovrebbe tornare bozza

        //controllo dati
        if (middlewareHead.check(prodottoModificato)) {
            repoProdotti.put(id, prodottoModificato);
        } else {
            throw new DatiIncorrettiException("I dati modificati non sono accettabili.");
        }
    }

    public void modificaPacchetto(int id, Pacchetto pacchetto) {
        if (!repoPacchetti.containsKey(id)) {
            throw new ProdottoNonTrovatoException("Non esiste pacchetto con id " + id);
        }

        if (pacchetto.getPrezzo() <= 0) throw new DatiIncorrettiException("I dati modificati non sono accettabili.");


        for (Prodotto p : pacchetto.getListaProdotti()) {
            if (!middlewareHead.check(p)) {
                throw new DatiIncorrettiException("I dati modificati non sono accettabili.");
            }
        }

        pacchetto.cambiaStato(new Bozza(pacchetto));
        repoPacchetti.put(id, pacchetto);

    }

    /**
     * Metodo per aggiungere scorte ad un Prodotto.
     *
     * @param id Identificatore del prodotto
     * @param quantita Quantità da aggiungere al prodotto, sommata a quella attuale
     *
     * @throws ProdottoNonTrovatoException Se l'id non corrisponde a un Prodotto nel sistema.
     */
    public void restock(int id, int quantita) {
        Prodotto prodotto = repoProdotti.get(id);
        if (prodotto == null) throw new ProdottoNonTrovatoException("Non esiste prodotto con id " + id);

        int quantitaAttuale = prodotto.getQuantita();
        prodotto.setQuantita(quantitaAttuale + quantita);
        repoProdotti.put(id, prodotto);
    }

    /**
     * Elimina un prodotto con il suo id.
     *
     * @param id Identificatore prodotto
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     */
    public void eliminaProdotto(int id) {
        if (!repoProdotti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste prodotto con id " + id);
        repoProdotti.remove(id);
    }

    /**
     * Elimina un pacchetto con il suo id.
     *
     * @param id Identificatore pacchettp
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un pacchetto nel database
     */
    public void eliminaPacchetto(int id) {
        if (!repoPacchetti.containsKey(id)) throw new ProdottoNonTrovatoException(
                "Non esiste pacchetto con id " + id);
        repoPacchetti.remove(id);
    }

    //TODO
    public String generaLinkSocial() {
        return "";
    }

}
