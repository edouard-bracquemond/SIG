package fr.orleans.sig.model.geo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "espace_public_ev_banc")
public class Banc {
    private int gid;
    private String bancDetail;
    private String bancLoc;
    private String bancSecteu;
    private String bancRemarq;
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
    @Column(name = "banc_detail")
    public String getBancDetail() {
        return bancDetail;
    }

    public void setBancDetail(String bancDetail) {
        this.bancDetail = bancDetail;
    }

    @Basic
    @Column(name = "banc_loc")
    public String getBancLoc() {
        return bancLoc;
    }

    public void setBancLoc(String bancLoc) {
        this.bancLoc = bancLoc;
    }

    @Basic
    @Column(name = "banc_secteu")
    public String getBancSecteu() {
        return bancSecteu;
    }

    public void setBancSecteu(String bancSecteu) {
        this.bancSecteu = bancSecteu;
    }

    @Basic
    @Column(name = "banc_remarq")
    public String getBancRemarq() {
        return bancRemarq;
    }

    public void setBancRemarq(String bancRemarq) {
        this.bancRemarq = bancRemarq;
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
        Banc that = (Banc) o;
        return gid == that.gid &&
                Objects.equals(bancDetail, that.bancDetail) &&
                Objects.equals(bancLoc, that.bancLoc) &&
                Objects.equals(bancSecteu, that.bancSecteu) &&
                Objects.equals(bancRemarq, that.bancRemarq) &&
                Objects.equals(geom, that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, bancDetail, bancLoc, bancSecteu, bancRemarq, geom);
    }
}
