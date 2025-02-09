package it.unicam.cs.ids2425.filieraagricolalocale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Carrello;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.LineaOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Persona;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.TipoPOI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;

public class OrdineTest {
   @Test void testNuovoOrdine() {

      Venditore v = new Venditore("MEI", "123", null, null);

      Prodotto p = new Prodotto
      ("mela", null, 0, 1, null, new POI(0, 0, 0, TipoPOI.Azienda), null, null, null);

      Persona u = new Persona("Michele", "Mysser", null, "Delta", null);

      u.getCarrello().aggiungiProdotto(p, 2);

      Ordine o = new Ordine(null, u.getCarrello().getListaProdotti(), u, new Indirizzo(null, 0, null, null, null, null, null));

      for (LineaOrdine l : o.getArticoli()) {
         l.getProdotto().aggiungiOrdine(l);
      }

  }

}
