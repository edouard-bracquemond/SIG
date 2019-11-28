package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.DechetsPav;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DechetsRepository extends JpaRepository<DechetsPav, Long> {
}
