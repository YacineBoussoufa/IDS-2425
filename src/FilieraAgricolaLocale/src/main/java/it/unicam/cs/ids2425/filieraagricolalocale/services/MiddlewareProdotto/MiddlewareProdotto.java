package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareProdotto;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;

public abstract class MiddlewareProdotto {

    private MiddlewareProdotto next;

    /**
     * @param first
     * @param chain
     * @return
     */
    public static MiddlewareProdotto link(MiddlewareProdotto first, MiddlewareProdotto... chain) {
        MiddlewareProdotto head = first;
        for (MiddlewareProdotto nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * @param p
     * @return
     */
    public abstract boolean check(Contenuto p);

    /**
     * @param p
     * @return
     */
    public boolean checkNext(Contenuto p) {
        if(next == null){
            return true;
        }
        return next.check(p);
    }

}
