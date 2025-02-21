package it.unicam.cs.ids2425.filieraagricolalocale.controllers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
					.authorizeHttpRequests((requests) -> requests
							.requestMatchers("/account/listaVenditori").hasRole("Gestore")
							.requestMatchers("/account/listaUtenti").permitAll()
							.anyRequest().authenticated()
							// .requestMatchers("/autenticato/").authenticated()
							// .requestMatchers("/richieste/ruoli/", "/richieste/eliminazione/","/categorie/", "/gestisci/")
							// .hasRole("GESTORE_DELLA_PIATTAFORMA")
							// .requestMatchers("/operatore/", "/categorie").hasAnyRole("PRODUTTORE","TRASFORMATORE","DISTRIBUTORE_DI_TIPICITA")
							// .requestMatchers("/operatore/produttore").hasRole("PRODUTTORE")
							// .requestMatchers("/operatore/trasformatore").hasRole("TRASFORMATORE")
							// .requestMatchers("/operatore/distributore").hasRole("DISTRIBUTORE")
							// .requestMatchers("/richieste/validazione/").hasRole("CURATORE")
							// .requestMatchers("/attivita/**").hasRole("ANIMATORE_DELLA_FILIERA")
							//.anyRequest().authenticated()
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

}
