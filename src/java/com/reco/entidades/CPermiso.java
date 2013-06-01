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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hugo
 */
@Entity
@Table(name = "CPermiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CPermiso.findAll", query = "SELECT c FROM CPermiso c"),
    @NamedQuery(name = "CPermiso.findByIdPer", query = "SELECT c FROM CPermiso c WHERE c.idPer = :idPer"),
    @NamedQuery(name = "CPermiso.findByDesPer", query = "SELECT c FROM CPermiso c WHERE c.desPer = :desPer")})
public class CPermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_per")
    private Integer idPer;
    @Column(name = "des_per")
    private String desPer;
    @JoinTable(name = "EPermisoUsuario", joinColumns = {
        @JoinColumn(name = "id_per", referencedColumnName = "id_per")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tus", referencedColumnName = "id_tus")})
    @ManyToMany
    private Collection<MTipoUsuario> mTipoUsuarioCollection;

    public CPermiso() {
    }

    public CPermiso(Integer idPer) {
        this.idPer = idPer;
    }

    public Integer getIdPer() {
        return idPer;
    }

    public void setIdPer(Integer idPer) {
        this.idPer = idPer;
    }

    public String getDesPer() {
        return desPer;
    }

    public void setDesPer(String desPer) {
        this.desPer = desPer;
    }

    @XmlTransient
    public Collection<MTipoUsuario> getMTipoUsuarioCollection() {
        return mTipoUsuarioCollection;
    }

    public void setMTipoUsuarioCollection(Collection<MTipoUsuario> mTipoUsuarioCollection) {
        this.mTipoUsuarioCollection = mTipoUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPer != null ? idPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CPermiso)) {
            return false;
        }
        CPermiso other = (CPermiso) object;
        if ((this.idPer == null && other.idPer != null) || (this.idPer != null && !this.idPer.equals(other.idPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.CPermiso[ idPer=" + idPer + " ]";
    }
    
}
