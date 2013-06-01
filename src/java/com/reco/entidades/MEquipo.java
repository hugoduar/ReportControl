/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hugo
 */
@Entity
@Table(name = "MEquipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MEquipo.findAll", query = "SELECT m FROM MEquipo m"),
    @NamedQuery(name = "MEquipo.findByNomEqu", query = "SELECT m FROM MEquipo m WHERE m.nomEqu = :nomEqu")})
public class MEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nom_equ")
    private String nomEqu;
    @JoinColumn(name = "id_lab", referencedColumnName = "id_lab")
    @ManyToOne
    private MLaboratorio idLab;
    @OneToMany(mappedBy = "nomEqu")
    private Collection<MReporte> mReporteCollection;
    @OneToMany(mappedBy = "nomEqu")
    private Collection<MReparacion> mReparacionCollection;

    public MEquipo() {
    }

    public MEquipo(String nomEqu) {
        this.nomEqu = nomEqu;
    }

    public String getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(String nomEqu) {
        this.nomEqu = nomEqu;
    }

    public MLaboratorio getIdLab() {
        return idLab;
    }

    public void setIdLab(MLaboratorio idLab) {
        this.idLab = idLab;
    }

    @XmlTransient
    public Collection<MReporte> getMReporteCollection() {
        return mReporteCollection;
    }

    public void setMReporteCollection(Collection<MReporte> mReporteCollection) {
        this.mReporteCollection = mReporteCollection;
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
        hash += (nomEqu != null ? nomEqu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MEquipo)) {
            return false;
        }
        MEquipo other = (MEquipo) object;
        if ((this.nomEqu == null && other.nomEqu != null) || (this.nomEqu != null && !this.nomEqu.equals(other.nomEqu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MEquipo[ nomEqu=" + nomEqu + " ]";
    }
    
}
