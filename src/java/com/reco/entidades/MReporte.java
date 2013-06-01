/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hugo
 */
@Entity
@Table(name = "MReporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MReporte.findAll", query = "SELECT m FROM MReporte m"),
    @NamedQuery(name = "MReporte.findByIdRep", query = "SELECT m FROM MReporte m WHERE m.idRep = :idRep"),
    @NamedQuery(name = "MReporte.findByDesRep", query = "SELECT m FROM MReporte m WHERE m.desRep = :desRep"),
    @NamedQuery(name = "MReporte.findByFecRep", query = "SELECT m FROM MReporte m WHERE m.fecRep = :fecRep"),
    @NamedQuery(name = "MReporte.findByNomAsi", query = "SELECT m FROM MReporte m WHERE m.nomAsi = :nomAsi")})
public class MReporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rep")
    private Integer idRep;
    @Basic(optional = false)
    @Column(name = "des_rep")
    private String desRep;
    @Basic(optional = false)
    @Column(name = "fec_rep")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRep;
    @Column(name = "nom_asi")
    private String nomAsi;
    @JoinColumn(name = "nom_equ", referencedColumnName = "nom_equ")
    @ManyToOne
    private MEquipo nomEqu;
    @JoinColumn(name = "id_ece", referencedColumnName = "id_ece")
    @ManyToOne
    private EReporte idEce;
    @JoinColumn(name = "id_tfa", referencedColumnName = "id_tfa")
    @ManyToOne
    private CTipoFalla idTfa;
    @JoinColumn(name = "id_ere", referencedColumnName = "id_ere")
    @ManyToOne
    private CEstadoReporte idEre;
    @OneToMany(mappedBy = "idRep")
    private Collection<MReparacion> mReparacionCollection;

    public MReporte() {
    }

    public MReporte(Integer idRep) {
        this.idRep = idRep;
    }

    public MReporte(Integer idRep, String desRep, Date fecRep) {
        this.idRep = idRep;
        this.desRep = desRep;
        this.fecRep = fecRep;
    }

    public Integer getIdRep() {
        return idRep;
    }

    public void setIdRep(Integer idRep) {
        this.idRep = idRep;
    }

    public String getDesRep() {
        return desRep;
    }

    public void setDesRep(String desRep) {
        this.desRep = desRep;
    }

    public Date getFecRep() {
        return fecRep;
    }

    public void setFecRep(Date fecRep) {
        this.fecRep = fecRep;
    }

    public String getNomAsi() {
        return nomAsi;
    }

    public void setNomAsi(String nomAsi) {
        this.nomAsi = nomAsi;
    }

    public MEquipo getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(MEquipo nomEqu) {
        this.nomEqu = nomEqu;
    }

    public EReporte getIdEce() {
        return idEce;
    }

    public void setIdEce(EReporte idEce) {
        this.idEce = idEce;
    }

    public CTipoFalla getIdTfa() {
        return idTfa;
    }

    public void setIdTfa(CTipoFalla idTfa) {
        this.idTfa = idTfa;
    }

    public CEstadoReporte getIdEre() {
        return idEre;
    }

    public void setIdEre(CEstadoReporte idEre) {
        this.idEre = idEre;
    }

    @XmlTransient
    public Collection<MReparacion> getMReparacionCollection() {
        return mReparacionCollection;
    }

    public void setMReparacionCollection(Collection<MReparacion> mReparacionCollection) {
        this.mReparacionCollection = mReparacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRep != null ? idRep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MReporte)) {
            return false;
        }
        MReporte other = (MReporte) object;
        if ((this.idRep == null && other.idRep != null) || (this.idRep != null && !this.idRep.equals(other.idRep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MReporte[ idRep=" + idRep + " ]";
    }
    
}
