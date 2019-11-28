package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.Corbeilles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorbeillesRepository extends JpaRepository<Corbeilles, Long> {
}
