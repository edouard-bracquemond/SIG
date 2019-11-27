package fr.orleans.sig.model.geo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "espace_public_ev_sanitaires", schema = "public", catalog = "gisdb")
public class Sanitaires {
    private int gid;
    private BigInteger id;
    private String sanGest;
    private String sanHoraire;
    private String sanLoc;
    private String sanSect;
    private String sanRemarq;
    private String sanHandi;
    private BigInteger sanNum;
    private String codcomm;
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
    @Column(name = "san_gest")
    public String getSanGest() {
        return sanGest;
    }

    public void setSanGest(String sanGest) {
        this.sanGest = sanGest;
    }

    @Basic
    @Column(name = "san_horaire")
    public String getSanHoraire() {
        return sanHoraire;
    }

    public void setSanHoraire(String sanHoraire) {
        this.sanHoraire = sanHoraire;
    }

    @Basic
    @Column(name = "san_loc")
    public String getSanLoc() {
        return sanLoc;
    }

    public void setSanLoc(String sanLoc) {
        this.sanLoc = sanLoc;
    }

    @Basic
    @Column(name = "san_sect")
    public String getSanSect() {
        return sanSect;
    }

    public void setSanSect(String sanSect) {
        this.sanSect = sanSect;
    }

    @Basic
    @Column(name = "san_remarq")
    public String getSanRemarq() {
        return sanRemarq;
    }

    public void setSanRemarq(String sanRemarq) {
        this.sanRemarq = sanRemarq;
    }

    @Basic
    @Column(name = "san_handi")
    public String getSanHandi() {
        return sanHandi;
    }

    public void setSanHandi(String sanHandi) {
        this.sanHandi = sanHandi;
    }

    @Basic
    @Column(name = "san_num")
    public BigInteger getSanNum() {
        return sanNum;
    }

    public void setSanNum(BigInteger sanNum) {
        this.sanNum = sanNum;
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
        Sanitaires that = (Sanitaires) o;
        return gid == that.gid &&
                Objects.equals(id, that.id) &&
                Objects.equals(sanGest, that.sanGest) &&
                Objects.equals(sanHoraire, that.sanHoraire) &&
                Objects.equals(sanLoc, that.sanLoc) &&
                Objects.equals(sanSect, that.sanSect) &&
                Objects.equals(sanRemarq, that.sanRemarq) &&
                Objects.equals(sanHandi, that.sanHandi) &&
                Objects.equals(sanNum, that.sanNum) &&
                Objects.equals(codcomm, that.codcomm) &&
                Objects.equals(geom, that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, id, sanGest, sanHoraire, sanLoc, sanSect, sanRemarq, sanHandi, sanNum, codcomm, geom);
    }
}
