package it.unicam.cs.ids2425.filieraagricolalocale.controllers.DTO;

import it.unicam.cs.ids2425.filieraagricolalocale.model.TipoPOI;


public class POIDTO {
   	private double Latitudine;
	private double Longitudine;
	private int Altitudine;
	
	private TipoPOI tipo;

   public POIDTO(double latitudine, double longitudine, int altitudine, TipoPOI tipo) {
      Latitudine = latitudine;
      Longitudine = longitudine;
      Altitudine = altitudine;
      this.tipo = tipo;
   }

   public double getLatitudine() {
      return Latitudine;
   }

   public void setLatitudine(double latitudine) {
      Latitudine = latitudine;
   }

   public double getLongitudine() {
      return Longitudine;
   }

   public void setLongitudine(double longitudine) {
      Longitudine = longitudine;
   }

   public int getAltitudine() {
      return Altitudine;
   }

   public void setAltitudine(int altitudine) {
      Altitudine = altitudine;
   }

   public TipoPOI getTipo() {
      return tipo;
   }

   public void setTipo(TipoPOI tipo) {
      this.tipo = tipo;
   }
}
