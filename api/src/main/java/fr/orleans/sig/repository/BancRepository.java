package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.Banc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface BancRepository extends JpaRepository<Banc, Integer> {
}
