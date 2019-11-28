package fr.orleans.sig.model.geo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "dechets_pav")
public class DechetsPav {
    private int gid;
    private BigInteger objectid;
    private String typeFlux;
    private String codeCommu;
    private String rue;
    private String domanialit;
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
    @Column(name = "type_flux")
    public String getTypeFlux() {
        return typeFlux;
    }

    public void setTypeFlux(String typeFlux) {
        this.typeFlux = typeFlux;
    }

    @Basic
    @Column(name = "code_commu")
    public String getCodeCommu() {
        return codeCommu;
    }

    public void setCodeCommu(String codeCommu) {
        this.codeCommu = codeCommu;
    }

    @Basic
    @Column(name = "rue")
    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    @Basic
    @Column(name = "domanialit")
    public String getDomanialit() {
        return domanialit;
    }

    public void setDomanialit(String domanialit) {
        this.domanialit = domanialit;
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
        DechetsPav that = (DechetsPav) o;
        return gid == that.gid &&
                Objects.equals(objectid, that.objectid) &&
                Objects.equals(typeFlux, that.typeFlux) &&
                Objects.equals(codeCommu, that.codeCommu) &&
                Objects.equals(rue, that.rue) &&
                Objects.equals(domanialit, that.domanialit) &&
                Objects.equals(geom, that.geom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid, objectid, typeFlux, codeCommu, rue, domanialit, geom);
    }
}
