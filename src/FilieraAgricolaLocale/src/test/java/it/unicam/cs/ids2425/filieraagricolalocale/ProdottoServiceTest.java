package it.unicam.cs.ids2425.filieraagricolalocale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.unicam.cs.ids2425.filieraagricolalocale.model.InConvalida;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.ProdottoBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Pubblicato;
import it.unicam.cs.ids2425.filieraagricolalocale.model.StatoApprovazione;

@SpringBootTest
class ProdottoServiceTest {
/* 
	@Test
   public void visualizzaProdottiStato() {
      Map<Integer, Prodotto> repoProdotti = new HashMap<>();

      repoProdotti.put(1, new ProdottoBuilder().setNome("mela").build());
      repoProdotti.put(2, new ProdottoBuilder().setNome("fieno").build());

      repoProdotti.get(2).getStato().pubblica();
      InConvalida a = (InConvalida) repoProdotti.get(2).getStato();
      a.approva();
      repoProdotti.get(2).getStato().pubblica();

      assertEquals(repoProdotti.values().stream().findFirst().get().getNome(), "mela");

      Stream<Prodotto> s = repoProdotti.values().stream().filter(p -> p.getStato() instanceof Pubblicato);

      assertEquals(s.findFirst().get().getNome(), "fieno");

    } */

}
