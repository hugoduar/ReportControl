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
import javax.persistence.ManyToMany;
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
@Table(name = "MTipoUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MTipoUsuario.findAll", query = "SELECT m FROM MTipoUsuario m"),
    @NamedQuery(name = "MTipoUsuario.findByIdTus", query = "SELECT m FROM MTipoUsuario m WHERE m.idTus = :idTus"),
    @NamedQuery(name = "MTipoUsuario.findByDesTus", query = "SELECT m FROM MTipoUsuario m WHERE m.desTus = :desTus"),
    @NamedQuery(name = "MTipoUsuario.findByTieSem", query = "SELECT m FROM MTipoUsuario m WHERE m.tieSem = :tieSem")})
public class MTipoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tus")
    private Integer idTus;
    @Column(name = "des_tus")
    private String desTus;
    @Basic(optional = false)
    @Column(name = "tie_sem")
    private int tieSem;
    @ManyToMany(mappedBy = "mTipoUsuarioCollection")
    private Collection<CPermiso> cPermisoCollection;
    @OneToMany(mappedBy = "idTus")
    private Collection<MUsuario> mUsuarioCollection;

    public MTipoUsuario() {
    }

    public MTipoUsuario(Integer idTus) {
        this.idTus = idTus;
    }

    public MTipoUsuario(Integer idTus, int tieSem) {
        this.idTus = idTus;
        this.tieSem = tieSem;
    }

    public Integer getIdTus() {
        return idTus;
    }

    public void setIdTus(Integer idTus) {
        this.idTus = idTus;
    }

    public String getDesTus() {
        return desTus;
    }

    public void setDesTus(String desTus) {
        this.desTus = desTus;
    }

    public int getTieSem() {
        return tieSem;
    }

    public void setTieSem(int tieSem) {
        this.tieSem = tieSem;
    }

    @XmlTransient
    public Collection<CPermiso> getCPermisoCollection() {
        return cPermisoCollection;
    }

    public void setCPermisoCollection(Collection<CPermiso> cPermisoCollection) {
        this.cPermisoCollection = cPermisoCollection;
    }

    @XmlTransient
    public Collection<MUsuario> getMUsuarioCollection() {
        return mUsuarioCollection;
    }

    public void setMUsuarioCollection(Collection<MUsuario> mUsuarioCollection) {
        this.mUsuarioCollection = mUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTus != null ? idTus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MTipoUsuario)) {
            return false;
        }
        MTipoUsuario other = (MTipoUsuario) object;
        if ((this.idTus == null && other.idTus != null) || (this.idTus != null && !this.idTus.equals(other.idTus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MTipoUsuario[ idTus=" + idTus + " ]";
    }
    
}
