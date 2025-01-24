package it.unicam.cs.ids2425.filieraagricolalocale;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class Prodotto implements Contenuto{

    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private StatoApprovazione statoApprovazione;
    private POI poi;
    private Date data;
    private final Set<Certificazione> listaCertificazioni;
    private final Set<Prodotto> ingredienti;
    private final List<LineaOrdine> ordini = new ArrayList<>();

    /**
     * Costruttore di una oggetto Prodotto. Lo Stato di approvazione iniziale viene messo automaticamente
     * a Bozza alla creazione di una istanza.
     *
     * @param nome Nome del prodotto.
     * @param descrizione Descrizione del prodotto.
     * @param prezzo Prezzo del prodotto.
     * @param quantita Quantità disponibile del prodotto.
     * @param poi Punto di Interesse.
     * @param data Data di produzione.
     * @param certificazioni Certificazioni di qualità del prodotto.
     * @param ingredienti Ingredienti del prodotto.
     */
    public Prodotto(String nome, String descrizione, double prezzo, int quantita, POI poi, Date data,
                    Set<Certificazione> certificazioni, Set<Prodotto> ingredienti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.poi = poi;
        this.data = data;
        this.ingredienti = new HashSet<>();
        this.ingredienti.addAll(ingredienti);
        listaCertificazioni = new HashSet<>();
        listaCertificazioni.addAll(certificazioni);
        this.statoApprovazione = new Bozza(this);
    }

    @Override
    public StatoApprovazione getStato() {
        return statoApprovazione;
    }

    @Override
    public void cambiaStato(StatoApprovazione stato) {
        this.statoApprovazione = stato;
    }

    public void aggiungiCertificazione(Certificazione certificazione) {
        listaCertificazioni.add(certificazione);
    }

    public void deleteCertificazione(Certificazione certificazione) {
        listaCertificazioni.remove(certificazione);
    }

    public void aggiungiIngrediente(Prodotto prodotto) {
        ingredienti.add(prodotto);
    }

    public void rimuoviIngrediente(Prodotto prodotto) {
        ingredienti.remove(prodotto);
    }

    /**
     * Aggiunge un ordine effettuato per il prodotto, aggiornando la quantità.
     *
     * @param lineaOrdine Ordine da aggiungere.
     * @return True se l'ordine è possibile, false altrimenti.
     */
    public boolean aggiungiOrdine(LineaOrdine lineaOrdine) {
        if (lineaOrdine.getQuantita() > quantita) {
            return false;
        }
        ordini.add(lineaOrdine);
        quantita -= lineaOrdine.getQuantita();
        return true;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Set<Certificazione> getListaCertificazioni() {
        return listaCertificazioni;
    }

    public Set<Prodotto> getIngredienti() {
        return ingredienti;
    }

    public List<LineaOrdine> getOrdini() {
        return ordini;
    }

    public POI getPoi() {
        return poi;
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
