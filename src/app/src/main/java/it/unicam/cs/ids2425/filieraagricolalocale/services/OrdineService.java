package it.unicam.cs.ids2425.filieraagricolalocale.services;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Indirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.model.LineaOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Ordine;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;

public class OrdineService {
   
   public static Map<Ordine, List<LineaOrdine>> userRepository;

   public void creaOrdine(DateFormat dataCreazione, Map<Prodotto, Integer> mappaProdotti, Utente u, Indirizzo i){
      Ordine o = new Ordine(dataCreazione, mappaProdotti, u, i);
      userRepository.put(o, o.getArticoli());
   }


}
