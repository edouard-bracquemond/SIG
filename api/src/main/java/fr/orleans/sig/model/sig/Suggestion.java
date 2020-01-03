package fr.orleans.sig.model.sig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.orleans.sig.model.user.User;

import javax.persistence.*;

@Entity
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String pseudo;

    public Suggestion(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
