package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.HashSet;
import java.util.Set;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Manifestazione;
import it.unicam.cs.ids2425.filieraagricolalocale.model.ManifestazioneBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Utente;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Venditore;
import it.unicam.cs.ids2425.filieraagricolalocale.model.Visita;
import it.unicam.cs.ids2425.filieraagricolalocale.model.VisitaBuilder;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;

public class ContenutoMapper {
   
   // public static Prodotto ToEntity(VisitaDTO evento, UserService us){
   //    VisitaBuilder builder = new VisitaBuilder();
   //    Set<Utente> personeP = new HashSet<>();
   //    for (String id : evento.getPersonePartecipanti()) {
   //          personeP.add(us.getUtente(id));
   //    }
      
   //    Visita eventoV = builder.setNome(evento.getNome())
   //          .setData(evento.getData())
   //          .setNumeroMaxPartecipanti(evento.getNumeroMaxPartecipanti())
   //          .setDescrizione(evento.getDescrizione())
   //          .setPuntoDiInteresse(new POI(evento.getPuntoDiInteresse().getLatitudine(), 
   //                                  evento.getPuntoDiInteresse().getLongitudine(), 
   //                                  evento.getPuntoDiInteresse().getAltitudine(), evento.getPuntoDiInteresse().getTipo()))
   //          .setAnimatore(us.getUtente(evento.getAnimatore()))
   //          .setPropostaVenditore(us.getVenditore(evento.getPropostaVenditore()))
   //          .setPersonePartecipanti(personeP).build();
   //    return eventoV;
   // }
   // public static Pacchetto ToEntity(ManifestazioneDTO evento, UserService us){
   //    ManifestazioneBuilder builder = new ManifestazioneBuilder();
   //    Set<Utente> personeP = new HashSet<>();
   //    for (String id : evento.getPersonePartecipanti()) {
   //          personeP.add(us.getUtente(id));
   //    }
   //    Set<Venditore> venditoreP = new HashSet<>();
   //    for (String id : evento.getAziendePartecipanti()) {
   //          venditoreP.add(us.getVenditore(id));
   //    }
      
   //    Manifestazione eventoV = builder.setNome(evento.getNome())
   //          .setData(evento.getData())
   //          .setNumeroMaxPartecipanti(evento.getNumeroMaxPartecipanti())
   //          .setDescrizione(evento.getDescrizione())
   //          .setPuntoDiInteresse(new POI(evento.getPuntoDiInteresse().getLatitudine(), 
   //                                  evento.getPuntoDiInteresse().getLongitudine(), 
   //                                  evento.getPuntoDiInteresse().getAltitudine(), evento.getPuntoDiInteresse().getTipo()))
   //          .setAnimatore(us.getUtente(evento.getAnimatore()))
   //          .setAziendePartecipanti(venditoreP)
   //          .setPersonePartecipanti(personeP).build();
   //    return eventoV;
   // }

}
