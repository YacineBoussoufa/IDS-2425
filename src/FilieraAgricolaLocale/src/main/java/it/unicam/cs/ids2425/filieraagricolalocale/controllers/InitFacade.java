package it.unicam.cs.ids2425.filieraagricolalocale.controllers;

import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloUtente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.RuoloVenditore;
import it.unicam.cs.ids2425.filieraagricolalocale.model.TipoPOI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.services.*;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEvento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento.MiddlewareEventoDati;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareIndirizzo;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareOrdine;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewarePagamento;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewarePubblicato;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareOrdine.MiddlewareQuantita;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareDati;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewarePOI;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto.MiddlewareProdotto;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente.MiddlewareUsername;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente.MiddlewareUtente;

@Component
@Scope("singleton")
public class InitFacade {

   private final ApprovazioneService aS;
   private final EventoService eS;
   private final MarketplaceService mS;
   private final OrdineService oS;
   private final OSMService osmS;
   private final ProdottoService pS;
   private final UserService uS;
   private final AutorizzazioneService authS;

   @Autowired
   public InitFacade(ApprovazioneService aS, EventoService eS, MarketplaceService mS, OrdineService oS, OSMService osmS,
         ProdottoService pS, UserService uS, AutorizzazioneService AuthS) {
      this.aS = aS;
      this.eS = eS;
      this.mS = mS;
      this.oS = oS;
      this.osmS = osmS;
      this.pS = pS;
      this.uS = uS;
      this.authS = AuthS;
      this.initMiddlewareEvento();
      this.initMiddlewareOrdine();
      this.initMiddlewareProdotto();
      this.initMiddlewareUtente();
      this.init();
   }

   private void init(){
      List<RuoloUtente> l = new LinkedList<>();
      l.add(RuoloUtente.Gestore);
      l.add(RuoloUtente.Curatore);
      l.add(RuoloUtente.Animatore);
      this.uS.creaUtente(new Utente("Michele", "Antiqus", Date.from(Instant.now()), "micky", "ciao", l));
      List<RuoloVenditore> lv = new LinkedList<>();
      lv.add(RuoloVenditore.Produttore);
      this.uS.creaVenditore("La Tana", "123", "key", "123", lv, "grossa fabbrica",
              new POI(0, 0, 0, TipoPOI.Azienda));
   }

   private void initMiddlewareEvento(){
      MiddlewareEvento me = MiddlewareEvento.link(new MiddlewareEventoDati(eS));
      eS.setMiddleware(me);
   }

   private void initMiddlewareOrdine(){
      MiddlewareOrdine m = MiddlewareOrdine.link(new MiddlewareIndirizzo(), new MiddlewarePagamento(), 
      new MiddlewareQuantita(mS, pS), new MiddlewarePubblicato(mS));
      this.oS.setMiddleware(m);
   }

   private void initMiddlewareProdotto(){
      MiddlewareProdotto mp = MiddlewareProdotto.link(new MiddlewareDati(), new MiddlewarePOI());
      pS.setMiddleware(mp);
   }

   private void initMiddlewareUtente(){
      MiddlewareUtente m = MiddlewareUtente.link(new MiddlewareUsername(uS));
      this.uS.setMiddleware(m);
   }

   public ApprovazioneService getaS() {
      return aS;
   }
   public EventoService geteS() {
      return eS;
   }
   public MarketplaceService getmS() {
      return mS;
   }
   public OrdineService getoS() {
      return oS;
   }
   public OSMService getOsmS() {
      return osmS;
   }
   public ProdottoService getpS() {
      return pS;
   }
   public UserService getuS() {
      return uS;
   }
   public AutorizzazioneService getAuthS() {
      return authS;
   }

}
