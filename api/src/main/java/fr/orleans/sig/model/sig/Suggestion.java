package fr.orleans.sig.model.sig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suggestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    public Suggestion(){}
}
