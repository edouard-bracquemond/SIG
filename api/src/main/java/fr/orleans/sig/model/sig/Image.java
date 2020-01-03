package fr.orleans.sig.model.sig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.orleans.sig.model.geo.Arbres;
import fr.orleans.sig.model.user.User;

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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String pseudo;

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
