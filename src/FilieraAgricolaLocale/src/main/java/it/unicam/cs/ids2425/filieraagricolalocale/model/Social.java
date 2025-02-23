package it.unicam.cs.ids2425.filieraagricolalocale.model;

import it.unicam.cs.ids2425.filieraagricolalocale.exceptions.DatiIncorrettiException;

public class Social {

    public static final String page = "localhost:8080/marketplace/contenuti/";

    public static String generateLink(String site, int id) {
        if (site.equalsIgnoreCase("Twitter") || site.equalsIgnoreCase("X")) {
            return "https://x.com/intent/tweet?url=" + page + id;
        } else if (site.equalsIgnoreCase("Facebook")) {
            return "https://www.facebook.com/sharer/sharer.php?u=" + page + id;
        } else if (site.equalsIgnoreCase("Whatsapp")) {
            return "https://api.whatsapp.com/send?text=" + page + id;
        } else {
            throw new DatiIncorrettiException("Non è stato implementato il link per " + site);
        }
    }

}
