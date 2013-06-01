/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hugo
 */
@Entity
@Table(name = "MReparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MReparacion.findAll", query = "SELECT m FROM MReparacion m"),
    @NamedQuery(name = "MReparacion.findByIdRci", query = "SELECT m FROM MReparacion m WHERE m.idRci = :idRci"),
    @NamedQuery(name = "MReparacion.findByDesReq", query = "SELECT m FROM MReparacion m WHERE m.desReq = :desReq"),
    @NamedQuery(name = "MReparacion.findByFecReq", query = "SELECT m FROM MReparacion m WHERE m.fecReq = :fecReq")})
public class MReparacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rci")
    private Integer idRci;
    @Basic(optional = false)
    @Column(name = "des_req")
    private String desReq;
    @Basic(optional = false)
    @Column(name = "fec_req")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecReq;
    @JoinColumn(name = "id_tec", referencedColumnName = "id_tec")
    @ManyToOne
    private MTecnico idTec;
    @JoinColumn(name = "id_rep", referencedColumnName = "id_rep")
    @ManyToOne
    private MReporte idRep;
    @JoinColumn(name = "nom_equ", referencedColumnName = "nom_equ")
    @ManyToOne
    private MEquipo nomEqu;

    public MReparacion() {
    }

    public MReparacion(Integer idRci) {
        this.idRci = idRci;
    }

    public MReparacion(Integer idRci, String desReq, Date fecReq) {
        this.idRci = idRci;
        this.desReq = desReq;
        this.fecReq = fecReq;
    }

    public Integer getIdRci() {
        return idRci;
    }

    public void setIdRci(Integer idRci) {
        this.idRci = idRci;
    }

    public String getDesReq() {
        return desReq;
    }

    public void setDesReq(String desReq) {
        this.desReq = desReq;
    }

    public Date getFecReq() {
        return fecReq;
    }

    public void setFecReq(Date fecReq) {
        this.fecReq = fecReq;
    }

    public MTecnico getIdTec() {
        return idTec;
    }

    public void setIdTec(MTecnico idTec) {
        this.idTec = idTec;
    }

    public MReporte getIdRep() {
        return idRep;
    }

    public void setIdRep(MReporte idRep) {
        this.idRep = idRep;
    }

    public MEquipo getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(MEquipo nomEqu) {
        this.nomEqu = nomEqu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRci != null ? idRci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MReparacion)) {
            return false;
        }
        MReparacion other = (MReparacion) object;
        if ((this.idRci == null && other.idRci != null) || (this.idRci != null && !this.idRci.equals(other.idRci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MReparacion[ idRci=" + idRci + " ]";
    }
    
}
