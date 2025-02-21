 package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

public interface ContenutoBuilder {

   ContenutoBuilder setId(int id);
   int getId();
   ContenutoBuilder setNome(String nome);
   String getNome();
   ContenutoBuilder setDescrizione(String descrizione);
   String getDescrizione();
   ContenutoBuilder setPrezzo(double prezzo);
   double getPrezzo();
   ContenutoBuilder setQuantita(int q);
   int getQuantita();
   ContenutoBuilder setVenditore(Venditore v);
   Venditore getVenditore();
   ContenutoBuilder setData(Date d);
   Date getData();

}