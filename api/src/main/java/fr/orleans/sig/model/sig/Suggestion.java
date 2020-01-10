package fr.orleans.sig.model.sig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import fr.orleans.sig.model.user.User;

import javax.persistence.*;

@Entity
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private String coords;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String pseudo;
    @JsonIgnore
    private Geometry geom;

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public Suggestion(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
