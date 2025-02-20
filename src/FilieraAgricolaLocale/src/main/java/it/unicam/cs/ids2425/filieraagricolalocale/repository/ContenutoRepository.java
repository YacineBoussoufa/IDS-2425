package it.unicam.cs.ids2425.filieraagricolalocale.repository;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenutoRepository extends JpaRepository<Contenuto, Integer> {

    List<Contenuto> findByVenditoreUsername(String username);

    List<Contenuto> findByTipo(String tipo);
}
