package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VisitaDTO{

    private Date data;
    private String nome;
    private String descrizione;
    private int numeroMaxPartecipanti;
    private POIDTO puntoDiInteresse;
    private String animatore;
    private Set<String> personePartecipanti = new HashSet<>();
    private String propostaVenditore;
    
    public VisitaDTO(Date data, String nome, String descrizione, int numeroMaxPartecipanti, POIDTO puntoDiInteresse,
         String animatore, Set<String> personePartecipanti, String propostaVenditore) {
      this.data = data;
      this.nome = nome;
      this.descrizione = descrizione;
      this.numeroMaxPartecipanti = numeroMaxPartecipanti;
      this.puntoDiInteresse = puntoDiInteresse;
      this.animatore = animatore;
      this.personePartecipanti = personePartecipanti;
      this.propostaVenditore = propostaVenditore;
   }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public int getNumeroMaxPartecipanti() {
        return numeroMaxPartecipanti;
    }
    public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
        this.numeroMaxPartecipanti = numeroMaxPartecipanti;
    }
    public POIDTO getPuntoDiInteresse() {
        return puntoDiInteresse;
    }
    public void setPuntoDiInteresse(POIDTO puntoDiInteresse) {
        this.puntoDiInteresse = puntoDiInteresse;
    }
    public String getAnimatore() {
        return animatore;
    }
    public void setAnimatore(String animatore) {
        this.animatore = animatore;
    }
    public Set<String> getPersonePartecipanti() {
        return personePartecipanti;
    }
    public void setPersonePartecipanti(Set<String> personePartecipanti) {
        this.personePartecipanti = personePartecipanti;
    }
    public String getPropostaVenditore() {
        return propostaVenditore;
    }
    public void setPropostaVenditore(String propostaVenditore) {
        this.propostaVenditore = propostaVenditore;
    }

}