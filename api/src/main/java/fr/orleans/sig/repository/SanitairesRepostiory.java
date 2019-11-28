package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.Sanitaires;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SanitairesRepostiory extends JpaRepository<Sanitaires, Long> {
}
