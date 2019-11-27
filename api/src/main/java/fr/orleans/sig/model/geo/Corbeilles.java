package fr.orleans.sig.model.geo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "espace_public_ev_corbeilles", schema = "public", catalog = "gisdb")
public class Corbeilles {
    private int gid;
    private BigInteger id;
    private String corbeilleT;
    private String corbeilleL;
    private String corbeilleS;
    private String corbeilleR;
    private String corbeilleF;
    private String corbeilleL6;
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
    @Column(name = "id")
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "corbeille_t")
    public String getCorbeilleT() {
        return corbeilleT;
    }

    public void setCorbeilleT(String corbeilleT) {
        this.corbeilleT = corbeilleT;
    }

    @Basic
    @Column(name = "corbeille_l")
    public String getCorbeilleL() {
        return corbeilleL;
    }

    public void setCorbeilleL(String corbeilleL) {
        this.corbeilleL = corbeilleL;
    }

    @Basic
    @Column(name = "corbeille_s")
    public String getCorbeilleS() {
        return corbeilleS;
    }

    public void setCorbeilleS(String corbeilleS) {
        this.corbeilleS = corbeilleS;
    }

    @Basic
    @Column(name = "corbeille_r")
    public String getCorbeilleR() {
        return corbeilleR;
    }

    public void setCorbeilleR(String corbeilleR) {
        this.corbeilleR = corbeilleR;
    }

    @Basic
    @Column(name = "corbeille_f")
    public String getCorbeilleF() {
        return corbeilleF;
    }

    public void setCorbeilleF(String corbeilleF) {
        this.corbeilleF = corbeilleF;
    }

    @Basic
    @Column(name = "corbeille_l__6")
    public String getCorbeilleL6() {
        return corbeilleL6;
    }

    public void setCorbeilleL6(String corbeilleL6) {
        this.corbeilleL6 = corbeilleL6;
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
        Corbeilles that = (Corbeilles) o;
        return gid == that.gid &&
                Objects.equals(id, that.id) &&
                Objects.equals(corbeilleT, that.corbeilleT) &&
                Objects.equals(corbeilleL, that.corbeilleL) &&
                Objects.equals(corbeilleS, that.corbeilleS) &&
                Objects.equals(corbeilleR, that.corbeilleR) &&
                Objects.equals(corbeilleF, that.corbeilleF) &&
                Objects.equals(corbeilleL6, that.corbeilleL6) &&
                Objects.equals(geom, that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, id, corbeilleT, corbeilleL, corbeilleS, corbeilleR, corbeilleF, corbeilleL6, geom);
    }
}
