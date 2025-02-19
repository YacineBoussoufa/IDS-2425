 package it.unicam.cs.ids2425.filieraagricolalocale.model;

import java.util.Date;

public interface ContenutoBuilder {

   ContenutoBuilder setNome(String nome);
   ContenutoBuilder setDescrizione(String descrizione);
   ContenutoBuilder setPrezzo(double prezzo);
   ContenutoBuilder setQuantita(int q);
   ContenutoBuilder setVenditore(Venditore v);
   ContenutoBuilder setData(Date d);

}