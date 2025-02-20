package it.unicam.cs.ids2425.filieraagricolalocale.repository;

import it.unicam.cs.ids2425.filieraagricolalocale.model.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface POIRepository extends JpaRepository<POI, Integer> {
}
