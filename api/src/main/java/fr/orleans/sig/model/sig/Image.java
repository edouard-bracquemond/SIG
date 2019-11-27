package fr.orleans.sig.model.sig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.orleans.sig.model.geo.Arbres;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    @Enumerated(EnumType.STRING)
    private Saison saison;
    private String url;
    @OneToOne
    private Arbres arbre;

    public Image(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public Arbres getArbre() {
        return arbre;
    }

    public void setArbre(Arbres arbre) {
        this.arbre = arbre;
    }
}
