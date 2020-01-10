package fr.orleans.sig.repository;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import fr.orleans.sig.model.sig.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

    @Query(
            value = "select con.nom_com from \"contours-iris-2016-epci\" as con WHERE ST_Contains(con.geom,ST_MakePoint(:longi, :lati))",
            nativeQuery = true)
    List<String> translateToGeom(@Param("longi") Double d1,
                                 @Param("lati") Double d2);


    @Transactional
    @Modifying
    @Query(
            value = "update suggestion set geom=(ST_MakePoint(?1,?2)) where id=?3",
            nativeQuery = true)
    void addGeom(Double d1, Double d2, Integer i);
}
