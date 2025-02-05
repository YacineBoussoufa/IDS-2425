package it.unicam.cs.ids2425.filieraagricolalocale.services;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;
import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.ProdottoNonTrovatoException;
import it.unicam.cs.ids2425.filieraagricolalocale.model.*;

import java.util.HashMap;
import java.util.Map;

public class ProdottoService {

    //todo MOCK DI UNA TABELLA PRODOTTI
    public static Map<Integer, Prodotto> repo = new HashMap<>();
    public static int idCounter = 0;

    /**
     * Crea un prodotto con un builder e controlla i dati inseriti.
     *
     * @param builder Builder per la creazione di un prodotto.
     *
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    public void creaProdotto(ProdottoBuilder builder) {
        int id = idCounter++;
        Prodotto prodotto = builder.setId(id).
                build();

        //controllo dati
        Handler handler = new ProdottoDatiHandler(prodotto);
        if (handler.handle()) {
            repo.put(id, prodotto);
        } else {
            throw new DatiIncorrettiException();
        }

        repo.put(id, prodotto);
    }

    /**
     * Modifica un prodotto a partire da un builder.
     * Setterà tutti i campi non vuoti con i campi inseriti dentro il builder.
     * Modificherà poi stato del prodotto in Bozza affinchè si proceda alla convalida delle modifiche.
     *
     * @param id ID del prodotto da modificare
     * @param modifiche Builder che contiene solo i campi da modificare
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     * @throws DatiIncorrettiException se i dati non sono accettati dall'handler
     */
    public void modificaProdotto(int id, ProdottoBuilder modifiche) {
        if (!repo.containsKey(id)) throw new ProdottoNonTrovatoException();
        Prodotto prodottoEsistente = repo.get(id);
        ProdottoBuilder attuale = ProdottoBuilder.copiaDa(prodottoEsistente);

        //tutti gli elementi non vuoti sono modificati
        attuale.setNome(modifiche.getNome() != null ? modifiche.getNome() : attuale.getNome());
        attuale.setDescrizione(modifiche.getDescrizione() != null ? modifiche.getDescrizione() : attuale.getDescrizione());
        attuale.setPrezzo(modifiche.getPrezzo() != 0 ? modifiche.getPrezzo() : attuale.getPrezzo());
        attuale.setQuantita(modifiche.getQuantita() != 0 ? modifiche.getQuantita() : attuale.getQuantita());
        attuale.setData(modifiche.getData() != null ? modifiche.getData() : attuale.getData());
        attuale.setPoi(modifiche.getPoi() != null ? modifiche.getPoi() : attuale.getPoi());
        attuale.setListaCertificazioni(!modifiche.getListaCertificazioni().isEmpty() ? modifiche.getListaCertificazioni()
                : attuale.getListaCertificazioni());
        attuale.setIngredienti(!modifiche.getIngredienti().isEmpty() ? modifiche.getIngredienti() : attuale.getIngredienti());

        //viene creato un nuovo prodotto da mettere allo stesso id; avrà stato Bozza
        Prodotto prodottoModificato = attuale.build();

        //controllo dati
        Handler handler = new ProdottoDatiHandler(prodottoModificato);
        if (handler.handle()) {
            repo.put(id, prodottoModificato);
        } else {
            throw new DatiIncorrettiException();
        }
    }

    /**
     * Elimina un prodotto con il suo id.
     *
     * @param id Identificatore prodotto
     *
     * @throws ProdottoNonTrovatoException se l'id non è associato a un prodotto nel database
     */
    public void eliminaProdotto(int id) {
        if (!repo.containsKey(id)) throw new ProdottoNonTrovatoException();
        repo.remove(id);
    }
}
