package it.unicam.cs.ids2425.filieraagricolalocale.prova;

import java.util.List;

public sealed interface user permits utente,azienda {
   
   String getUsername();

   String getPassword();

   List<ruol> getRuolo();

}
