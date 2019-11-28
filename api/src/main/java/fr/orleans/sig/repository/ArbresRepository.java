package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.Arbres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ArbresRepository extends JpaRepository<Arbres, Integer> {
    public Arbres findByGid(Long id);
}
