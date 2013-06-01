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
@Table(name = "MTecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MTecnico.findAll", query = "SELECT m FROM MTecnico m"),
    @NamedQuery(name = "MTecnico.findByIdTec", query = "SELECT m FROM MTecnico m WHERE m.idTec = :idTec"),
    @NamedQuery(name = "MTecnico.findByApaTec", query = "SELECT m FROM MTecnico m WHERE m.apaTec = :apaTec"),
    @NamedQuery(name = "MTecnico.findByAmaTec", query = "SELECT m FROM MTecnico m WHERE m.amaTec = :amaTec"),
    @NamedQuery(name = "MTecnico.findByNomTec", query = "SELECT m FROM MTecnico m WHERE m.nomTec = :nomTec"),
    @NamedQuery(name = "MTecnico.findByCorTec", query = "SELECT m FROM MTecnico m WHERE m.corTec = :corTec")})
public class MTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tec")
    private Integer idTec;
    @Basic(optional = false)
    @Column(name = "apa_tec")
    private String apaTec;
    @Basic(optional = false)
    @Column(name = "ama_tec")
    private String amaTec;
    @Basic(optional = false)
    @Column(name = "nom_tec")
    private String nomTec;
    @Basic(optional = false)
    @Column(name = "cor_tec")
    private String corTec;
    @OneToMany(mappedBy = "idTec")
    private Collection<MReparacion> mReparacionCollection;
    @JoinColumn(name = "id_usu", referencedColumnName = "id_usu")
    @ManyToOne
    private MUsuario idUsu;

    public MTecnico() {
    }

    public MTecnico(Integer idTec) {
        this.idTec = idTec;
    }

    public MTecnico(Integer idTec, String apaTec, String amaTec, String nomTec, String corTec) {
        this.idTec = idTec;
        this.apaTec = apaTec;
        this.amaTec = amaTec;
        this.nomTec = nomTec;
        this.corTec = corTec;
    }

    public Integer getIdTec() {
        return idTec;
    }

    public void setIdTec(Integer idTec) {
        this.idTec = idTec;
    }

    public String getApaTec() {
        return apaTec;
    }

    public void setApaTec(String apaTec) {
        this.apaTec = apaTec;
    }

    public String getAmaTec() {
        return amaTec;
    }

    public void setAmaTec(String amaTec) {
        this.amaTec = amaTec;
    }

    public String getNomTec() {
        return nomTec;
    }

    public void setNomTec(String nomTec) {
        this.nomTec = nomTec;
    }

    public String getCorTec() {
        return corTec;
    }

    public void setCorTec(String corTec) {
        this.corTec = corTec;
    }

    @XmlTransient
    public Collection<MReparacion> getMReparacionCollection() {
        return mReparacionCollection;
    }

    public void setMReparacionCollection(Collection<MReparacion> mReparacionCollection) {
        this.mReparacionCollection = mReparacionCollection;
    }

    public MUsuario getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(MUsuario idUsu) {
        this.idUsu = idUsu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTec != null ? idTec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MTecnico)) {
            return false;
        }
        MTecnico other = (MTecnico) object;
        if ((this.idTec == null && other.idTec != null) || (this.idTec != null && !this.idTec.equals(other.idTec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MTecnico[ idTec=" + idTec + " ]";
    }
    
}
