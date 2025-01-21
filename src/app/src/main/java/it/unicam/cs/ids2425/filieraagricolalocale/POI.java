package it.unicam.cs.ids2425.filieraagricolalocale;

public class POI implements Contenuto{
	
	private double Latitudine;
	private double Longitudine;
	private int Altitudine;
	private StatoApprovazione statoApprovazione;
	private TipoPOI tipo;
	
	public POI(double Latitudine, double Longitudine, int Altitudine, TipoPOI tipo) {
		this.Latitudine=Latitudine;
		this.Longitudine=Longitudine;
		this.Altitudine=Altitudine;
		this.tipo=tipo;
		
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
	
	public double getLatitudine() {
		return Latitudine;
	}
	
	public void setLatitudine(double Latitudine) {
		this.Latitudine=Latitudine;
	}
	
	public double getLongitudine() {
		return Longitudine;
	}
	
	public void setLongitudine(double Longitudine) {
		this.Longitudine=Longitudine;
	}
	
	public double getAltitudine() {
		return Altitudine;
	}
	
	public void setAltitudine(int Altitudine) {
		this.Altitudine=Altitudine;
	}
	
	public TipoPOI getTipoPOI() {
		return tipo;
	}
	
}