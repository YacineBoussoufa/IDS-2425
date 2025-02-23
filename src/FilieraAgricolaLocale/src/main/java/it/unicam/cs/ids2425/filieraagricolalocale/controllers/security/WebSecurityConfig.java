package it.unicam.cs.ids2425.filieraagricolalocale.controllers.security;

import it.unicam.cs.ids2425.filieraagricolalocale.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
					.authorizeHttpRequests((requests) -> requests
							// Account Controller
							.requestMatchers("/account/listaUtenti").hasRole("Gestore")
							.requestMatchers("/account/eliminaUtente/**").hasRole("Gestore")
							.requestMatchers("/account/eliminaVenditore/**").hasRole("Gestore")
							.requestMatchers("/account/modificaUtente/**").hasRole("Gestore")
							.requestMatchers("/account/modificaVenditore/**").hasRole("Gestore")
							.requestMatchers("/account/modificaRuoliVenditore/**").hasRole("Gestore")
							.requestMatchers("/account/modificaRuoliUtente/**").hasRole("Gestore")
							.requestMatchers("/account/listaVenditori").permitAll()
							.requestMatchers("/account/ricercaUtente/**").permitAll()
							.requestMatchers("/account/ricercaVenditore/**").permitAll()
							.requestMatchers("/account/creaUtente").permitAll()
							.requestMatchers("/account/creaVenditore").permitAll()
							// Ordini Controller
							.requestMatchers("/ordini/creaOrdine").permitAll()
							.requestMatchers("/ordini/ricercaOrdine/**").permitAll()
							.requestMatchers("/ordini/ricercaOrdineUtente/**").permitAll()
							.requestMatchers("/ordini/ricercaOrdineVenditore/**").permitAll()
							.requestMatchers("/ordini/modificaIndirizzo/**").permitAll()
							.requestMatchers("/ordini/modificaDataConsegna/**").hasAnyRole("Gestore", "Produttore", "Trasformatore", "Distributore")
							// Eventi Controller
							.requestMatchers("/*/crea").hasRole("Animatore")
							.requestMatchers("/modifica/**").hasRole("Animatore")
							.requestMatchers("/elimina/**").hasRole("Animatore")
							.requestMatchers("/visite").permitAll()
							.requestMatchers("/manifestazioni").permitAll()
							.requestMatchers("visite/nonAccettate").hasRole("Animatore")
							.requestMatchers("/accettaProposta/visita/*").hasAnyRole("Produttore", "Trasformatore", "Distributore")
							.requestMatchers("/aggiungi**").hasRole("Animatore")
							.requestMatchers("/**Partecipanti**").hasRole("Animatore")
							// Prodotto Controller
							.requestMatchers("/gestioneContenuti/**").hasAnyRole("Produttore", "Trasformatore", "Distributore")
							// Marketplace Controller
							.requestMatchers("/marketplace/**").permitAll()
							// Approvazione Controller
							.requestMatchers("/richiesta/**").hasAnyRole("Produttore", "Trasformatore", "Distributore")
							.requestMatchers("/convalida/**").hasRole("Curatore")
							// OSM Controller
							.requestMatchers("/mappa/**").permitAll()
							.anyRequest().authenticated()
					)
					.csrf(AbstractHttpConfigurer::disable)
					.headers(headers -> headers
										.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
					)
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // Permette di usare password in chiaro
	}

	@Bean
	public UserDetailsService userDetailsService(UserService userService) {
		return new MyUserDetails(userService);
	}


}
