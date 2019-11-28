package fr.orleans.sig.model.sig;

import javax.persistence.*;

@Entity
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @Enumerated(EnumType.STRING)
    private Equipement type;
    private Long idEquipement;


    public Report(){}

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

    public Equipement getType() {
        return type;
    }

    public void setType(Equipement type) {
        this.type = type;
    }

    public Long getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(Long idEquipement) {
        this.idEquipement = idEquipement;
    }
}
