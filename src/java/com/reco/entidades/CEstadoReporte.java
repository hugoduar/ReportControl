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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CEstadoReporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CEstadoReporte.findAll", query = "SELECT c FROM CEstadoReporte c"),
    @NamedQuery(name = "CEstadoReporte.findByIdEre", query = "SELECT c FROM CEstadoReporte c WHERE c.idEre = :idEre"),
    @NamedQuery(name = "CEstadoReporte.findByDesEre", query = "SELECT c FROM CEstadoReporte c WHERE c.desEre = :desEre")})
public class CEstadoReporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ere")
    private Integer idEre;
    @Basic(optional = false)
    @Column(name = "des_ere")
    private String desEre;
    @OneToMany(mappedBy = "idEre")
    private Collection<MReporte> mReporteCollection;

    public CEstadoReporte() {
    }

    public CEstadoReporte(Integer idEre) {
        this.idEre = idEre;
    }

    public CEstadoReporte(Integer idEre, String desEre) {
        this.idEre = idEre;
        this.desEre = desEre;
    }

    public Integer getIdEre() {
        return idEre;
    }

    public void setIdEre(Integer idEre) {
        this.idEre = idEre;
    }

    public String getDesEre() {
        return desEre;
    }

    public void setDesEre(String desEre) {
        this.desEre = desEre;
    }

    @XmlTransient
    public Collection<MReporte> getMReporteCollection() {
        return mReporteCollection;
    }

    public void setMReporteCollection(Collection<MReporte> mReporteCollection) {
        this.mReporteCollection = mReporteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEre != null ? idEre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CEstadoReporte)) {
            return false;
        }
        CEstadoReporte other = (CEstadoReporte) object;
        if ((this.idEre == null && other.idEre != null) || (this.idEre != null && !this.idEre.equals(other.idEre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.CEstadoReporte[ idEre=" + idEre + " ]";
    }
    
}
