package it.unicam.cs.ids2425.filieraagricolalocale.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class POI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double Latitudine;
	private double Longitudine;
	private int Altitudine;
	
	@Enumerated(EnumType.STRING)
	private TipoPOI tipo;
	
	public POI(double Latitudine, double Longitudine, int Altitudine, TipoPOI tipo) {
		this.Latitudine=Latitudine;
		this.Longitudine=Longitudine;
		this.Altitudine=Altitudine;
		this.tipo=tipo;
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
	
	@JsonProperty("tipo")
	public TipoPOI getTipoPOI() {
		return tipo;
	}
	
}