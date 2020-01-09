package fr.orleans.sig.repository;

import fr.orleans.sig.model.sig.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
}
