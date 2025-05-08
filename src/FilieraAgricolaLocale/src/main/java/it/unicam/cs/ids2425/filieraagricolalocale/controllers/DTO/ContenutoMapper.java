package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.HashSet;
import java.util.Set;


import it.unicam.cs.ids2425.filieraagricolalocale.model.Pacchetto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.PacchettoBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Prodotto;
import it.unicam.cs.ids2425.filieraagricolalocale.model.ProdottoBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MarketplaceService;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;

public class ContenutoMapper {
   
   // public static Contenuto ToEntity(ContenutoDTO c, MarketplaceService us, UserService s){
   //    if(c instanceof ProdottoDTO){
   //       return ToProdotto((ProdottoDTO) c, us, s);
   //    }
   //    else if(c instanceof PacchettoDTO){
   //       return ToPacchetto((PacchettoDTO) c, us, s);
   //    }

   //    return null;
   // }

   public static Prodotto ToProdotto(ProdottoDTO d, MarketplaceService us, UserService s){
      ProdottoBuilder builder = new ProdottoBuilder();
      Set<Prodotto> ingredienti = new HashSet<>();
      for (int id : d.getIngredienti()) {
         ingredienti.add(us.visualizzaProdotto(id));
      }
      Prodotto prodotto = builder.setNome(d.getNome())
            .setData(d.getData())
            .setDescrizione(d.getDescrizione())
            .setListaEtichette(d.getListaEtichette())
            .setIngredienti(ingredienti)
            .setVenditore(s.getVenditore(d.getVenditore()))
            .setPrezzo(d.getPrezzo())
            .setQuantita(d.getQuantita()).build();
      return prodotto;

   }
   
   public static Pacchetto ToPacchetto(PacchettoDTO d, MarketplaceService us, UserService s){
      PacchettoBuilder builder = new PacchettoBuilder();
      Set<Prodotto> prodotti = new HashSet<>();
      for (int id : d.getListaProdotti()) {
         prodotti.add(us.visualizzaProdotto(id));
      }

      Pacchetto pacchetto = builder.setNome(d.getNome())
            .setData(d.getData())
            .setDescrizione(d.getDescrizione())
            .setListaProdotti(prodotti)
            .setVenditore(s.getVenditore(d.getVenditore()))
            .setPrezzo(d.getPrezzo())
            .setQuantita(d.getQuantita()).build();

      return pacchetto;
   }

}
