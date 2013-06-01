/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "MUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MUsuario.findAll", query = "SELECT m FROM MUsuario m"),
    @NamedQuery(name = "MUsuario.findByIdUsu", query = "SELECT m FROM MUsuario m WHERE m.mUsuarioPK.idUsu = :idUsu"),
    @NamedQuery(name = "MUsuario.findByNicUsu", query = "SELECT m FROM MUsuario m WHERE m.mUsuarioPK.nicUsu = :nicUsu"),
    @NamedQuery(name = "MUsuario.findByConUsu", query = "SELECT m FROM MUsuario m WHERE m.conUsu = :conUsu")})
public class MUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MUsuarioPK mUsuarioPK;
    @Basic(optional = false)
    @Column(name = "con_usu")
    private String conUsu;
    @OneToMany(mappedBy = "idUsu")
    private Collection<MProfesor> mProfesorCollection;
    @JoinColumn(name = "id_tus", referencedColumnName = "id_tus")
    @ManyToOne
    private MTipoUsuario idTus;
    @OneToMany(mappedBy = "idUsu")
    private Collection<MAlumno> mAlumnoCollection;
    @OneToMany(mappedBy = "idUsu")
    private Collection<MTecnico> mTecnicoCollection;

    public MUsuario() {
    }

    public MUsuario(MUsuarioPK mUsuarioPK) {
        this.mUsuarioPK = mUsuarioPK;
    }

    public MUsuario(MUsuarioPK mUsuarioPK, String conUsu) {
        this.mUsuarioPK = mUsuarioPK;
        this.conUsu = conUsu;
    }

    public MUsuario(int idUsu, String nicUsu) {
        this.mUsuarioPK = new MUsuarioPK(idUsu, nicUsu);
    }

    public MUsuarioPK getMUsuarioPK() {
        return mUsuarioPK;
    }

    public void setMUsuarioPK(MUsuarioPK mUsuarioPK) {
        this.mUsuarioPK = mUsuarioPK;
    }

    public String getConUsu() {
        return conUsu;
    }

    public void setConUsu(String conUsu) {
        this.conUsu = conUsu;
    }

    @XmlTransient
    public Collection<MProfesor> getMProfesorCollection() {
        return mProfesorCollection;
    }

    public void setMProfesorCollection(Collection<MProfesor> mProfesorCollection) {
        this.mProfesorCollection = mProfesorCollection;
    }

    public MTipoUsuario getIdTus() {
        return idTus;
    }

    public void setIdTus(MTipoUsuario idTus) {
        this.idTus = idTus;
    }

    @XmlTransient
    public Collection<MAlumno> getMAlumnoCollection() {
        return mAlumnoCollection;
    }

    public void setMAlumnoCollection(Collection<MAlumno> mAlumnoCollection) {
        this.mAlumnoCollection = mAlumnoCollection;
    }

    @XmlTransient
    public Collection<MTecnico> getMTecnicoCollection() {
        return mTecnicoCollection;
    }

    public void setMTecnicoCollection(Collection<MTecnico> mTecnicoCollection) {
        this.mTecnicoCollection = mTecnicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mUsuarioPK != null ? mUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MUsuario)) {
            return false;
        }
        MUsuario other = (MUsuario) object;
        if ((this.mUsuarioPK == null && other.mUsuarioPK != null) || (this.mUsuarioPK != null && !this.mUsuarioPK.equals(other.mUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MUsuario[ mUsuarioPK=" + mUsuarioPK + " ]";
    }
    
}
