package fr.orleans.sig.model.geo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import fr.orleans.sig.model.sig.Image;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "espace_publicev_arbres")
public class Arbres {
    private int gid;
    private BigInteger objectid;
    private BigInteger idArbre;
    private BigInteger siNum;
    private String dateMajRm;
    private String nomSite;
    private String equipe;
    private String genre;
    private String espece;
    private String variete;
    private String datePlant;
    private String modeCond;
    private String codcomm;
    private String type;
    @JsonIgnore
    private Geometry geom;

    @Id
    @Column(name = "gid")
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "objectid")
    public BigInteger getObjectid() {
        return objectid;
    }

    public void setObjectid(BigInteger objectid) {
        this.objectid = objectid;
    }

    @Basic
    @Column(name = "id_arbre")
    public BigInteger getIdArbre() {
        return idArbre;
    }

    public void setIdArbre(BigInteger idArbre) {
        this.idArbre = idArbre;
    }

    @Basic
    @Column(name = "si_num")
    public BigInteger getSiNum() {
        return siNum;
    }

    public void setSiNum(BigInteger siNum) {
        this.siNum = siNum;
    }

    @Basic
    @Column(name = "date_maj_rm")
    public String getDateMajRm() {
        return dateMajRm;
    }

    public void setDateMajRm(String dateMajRm) {
        this.dateMajRm = dateMajRm;
    }

    @Basic
    @Column(name = "nom_site")
    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    @Basic
    @Column(name = "equipe")
    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "espece")
    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    @Basic
    @Column(name = "variete")
    public String getVariete() {
        return variete;
    }

    public void setVariete(String variete) {
        this.variete = variete;
    }

    @Basic
    @Column(name = "date_plant_")
    public String getDatePlant() {
        return datePlant;
    }

    public void setDatePlant(String datePlant) {
        this.datePlant = datePlant;
    }

    @Basic
    @Column(name = "mode_cond")
    public String getModeCond() {
        return modeCond;
    }

    public void setModeCond(String modeCond) {
        this.modeCond = modeCond;
    }

    @Basic
    @Column(name = "codcomm")
    public String getCodcomm() {
        return codcomm;
    }

    public void setCodcomm(String codcomm) {
        this.codcomm = codcomm;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "geom")
    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arbres that = (Arbres) o;
        return gid == that.gid &&
                Objects.equals(objectid, that.objectid) &&
                Objects.equals(idArbre, that.idArbre) &&
                Objects.equals(siNum, that.siNum) &&
                Objects.equals(dateMajRm, that.dateMajRm) &&
                Objects.equals(nomSite, that.nomSite) &&
                Objects.equals(equipe, that.equipe) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(espece, that.espece) &&
                Objects.equals(variete, that.variete) &&
                Objects.equals(datePlant, that.datePlant) &&
                Objects.equals(modeCond, that.modeCond) &&
                Objects.equals(codcomm, that.codcomm) &&
                Objects.equals(type, that.type) &&
                Objects.equals(geom, that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, objectid, idArbre, siNum, dateMajRm, nomSite, equipe, genre, espece, variete, datePlant, modeCond, codcomm, type, geom);
    }
}
