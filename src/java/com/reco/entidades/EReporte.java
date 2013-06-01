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
@Table(name = "EReporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EReporte.findAll", query = "SELECT e FROM EReporte e"),
    @NamedQuery(name = "EReporte.findByIdEce", query = "SELECT e FROM EReporte e WHERE e.idEce = :idEce")})
public class EReporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ece")
    private Integer idEce;
    @JoinColumn(name = "id_pro", referencedColumnName = "id_pro")
    @ManyToOne
    private MProfesor idPro;
    @JoinColumn(name = "id_alu", referencedColumnName = "id_alu")
    @ManyToOne
    private MAlumno idAlu;
    @OneToMany(mappedBy = "idEce")
    private Collection<MReporte> mReporteCollection;

    public EReporte() {
    }

    public EReporte(Integer idEce) {
        this.idEce = idEce;
    }

    public Integer getIdEce() {
        return idEce;
    }

    public void setIdEce(Integer idEce) {
        this.idEce = idEce;
    }

    public MProfesor getIdPro() {
        return idPro;
    }

    public void setIdPro(MProfesor idPro) {
        this.idPro = idPro;
    }

    public MAlumno getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(MAlumno idAlu) {
        this.idAlu = idAlu;
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
        hash += (idEce != null ? idEce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EReporte)) {
            return false;
        }
        EReporte other = (EReporte) object;
        if ((this.idEce == null && other.idEce != null) || (this.idEce != null && !this.idEce.equals(other.idEce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.EReporte[ idEce=" + idEce + " ]";
    }
    
}
