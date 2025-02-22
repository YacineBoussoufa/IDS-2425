package it.unicam.cs.ids2425.filieraagricolalocale.services.MiddlewareUtente;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;

public class MiddlewareUsername extends MiddlewareUtente {

   private UserService userS;

   public MiddlewareUsername(UserService p){
      this.userS = p;
   }

   @Override
  
   public boolean check(Account u) {

      if(userS.getAccount(u.getUsername()) != null) return false;

      return checkNext(u);
   }
}
