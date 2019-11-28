package fr.orleans.sig.repository;

import fr.orleans.sig.model.geo.Arbres;
import fr.orleans.sig.model.sig.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    public List<Image> findImageByArbre(Arbres arbres);
    public Collection<Image> findImageByArbre_Gid(Long i);
}
