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
@Table(name = "MLaboratorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MLaboratorio.findAll", query = "SELECT m FROM MLaboratorio m"),
    @NamedQuery(name = "MLaboratorio.findByIdLab", query = "SELECT m FROM MLaboratorio m WHERE m.idLab = :idLab"),
    @NamedQuery(name = "MLaboratorio.findByNomLab", query = "SELECT m FROM MLaboratorio m WHERE m.nomLab = :nomLab")})
public class MLaboratorio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lab")
    private Integer idLab;
    @Column(name = "nom_lab")
    private String nomLab;
    @OneToMany(mappedBy = "idLab")
    private Collection<MEquipo> mEquipoCollection;

    public MLaboratorio() {
    }

    public MLaboratorio(Integer idLab) {
        this.idLab = idLab;
    }

    public Integer getIdLab() {
        return idLab;
    }

    public void setIdLab(Integer idLab) {
        this.idLab = idLab;
    }

    public String getNomLab() {
        return nomLab;
    }

    public void setNomLab(String nomLab) {
        this.nomLab = nomLab;
    }

    @XmlTransient
    public Collection<MEquipo> getMEquipoCollection() {
        return mEquipoCollection;
    }

    public void setMEquipoCollection(Collection<MEquipo> mEquipoCollection) {
        this.mEquipoCollection = mEquipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLab != null ? idLab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MLaboratorio)) {
            return false;
        }
        MLaboratorio other = (MLaboratorio) object;
        if ((this.idLab == null && other.idLab != null) || (this.idLab != null && !this.idLab.equals(other.idLab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MLaboratorio[ idLab=" + idLab + " ]";
    }
    
}
