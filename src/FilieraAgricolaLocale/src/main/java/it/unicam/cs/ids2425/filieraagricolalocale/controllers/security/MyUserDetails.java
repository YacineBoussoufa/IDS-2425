package it.unicam.cs.ids2425.filieraagricolalocale.controllers.security;

import java.util.Collection;
import java.util.stream.Collectors;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;
import org.springframework.transaction.annotation.Transactional;

public class MyUserDetails implements UserDetailsService{

   private UserService userService;

   @Autowired
   public MyUserDetails(UserService uS){
      this.userService = uS;
   }

   @Transactional
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Account user = userService.getAccount(username);
      if(user==null) throw new UsernameNotFoundException("User not found");

      Collection<? extends GrantedAuthority> authorities = user.getListaRuoli().stream()
      .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getMessage())).collect(Collectors.toList());

        return new User(user.getUsername(),
                user.getPassword(),
                authorities);
   }
}
