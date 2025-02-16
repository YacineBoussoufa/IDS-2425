package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareEvento;

import it.unicam.cs.ids2425.filieraagricolalocale.model.EventoAbstract;
import it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente.MiddlewareUtente;

public abstract class MiddlewareEvento {
    
    private MiddlewareEvento next;

    public static MiddlewareEvento link(MiddlewareEvento first, MiddlewareEvento... chain) {
        MiddlewareEvento head = first;
        for (MiddlewareEvento nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(EventoAbstract e);

    public boolean checkNext(EventoAbstract e) {
        if(next == null){
            return true;
        }
        return next.check(e);
    }

}
