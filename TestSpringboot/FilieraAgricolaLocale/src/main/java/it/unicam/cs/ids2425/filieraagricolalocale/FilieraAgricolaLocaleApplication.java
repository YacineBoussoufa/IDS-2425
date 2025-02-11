package it.unicam.cs.ids2425.filieraagricolalocale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilieraAgricolaLocaleApplication {

	public static void main(String[] args) {

		//ps.creaProdotto(new ProdottoBuilder().setDescrizione("Prodotto bianco").setNome("Mela rossa").setData(new Date()).
		//setPoi(new POI(0, 0, 0, TipoPOI.Prodotto)).setQuantita(5).setVenditore(new Venditore(null, null, null, null))
		//.setPrezzo(20.0));

		SpringApplication.run(FilieraAgricolaLocaleApplication.class, args);
	}

}
