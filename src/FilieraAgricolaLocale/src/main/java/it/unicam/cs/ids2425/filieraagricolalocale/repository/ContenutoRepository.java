package it.unicam.cs.ids2425.filieraagricolalocale.repository;

import it.unicam.cs.ids2425.filieraagricolalocale.model.Contenuto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenutoRepository extends CrudRepository<Contenuto, Integer> {

}
