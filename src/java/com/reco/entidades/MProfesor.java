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
@Table(name = "MProfesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MProfesor.findAll", query = "SELECT m FROM MProfesor m"),
    @NamedQuery(name = "MProfesor.findByIdPro", query = "SELECT m FROM MProfesor m WHERE m.idPro = :idPro"),
    @NamedQuery(name = "MProfesor.findByApaPro", query = "SELECT m FROM MProfesor m WHERE m.apaPro = :apaPro"),
    @NamedQuery(name = "MProfesor.findByAmaPro", query = "SELECT m FROM MProfesor m WHERE m.amaPro = :amaPro"),
    @NamedQuery(name = "MProfesor.findByNomPro", query = "SELECT m FROM MProfesor m WHERE m.nomPro = :nomPro"),
    @NamedQuery(name = "MProfesor.findByCorPro", query = "SELECT m FROM MProfesor m WHERE m.corPro = :corPro")})
public class MProfesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pro")
    private Integer idPro;
    @Basic(optional = false)
    @Column(name = "apa_pro")
    private String apaPro;
    @Basic(optional = false)
    @Column(name = "ama_pro")
    private String amaPro;
    @Basic(optional = false)
    @Column(name = "nom_pro")
    private String nomPro;
    @Basic(optional = false)
    @Column(name = "cor_pro")
    private String corPro;
    @OneToMany(mappedBy = "idPro")
    private Collection<EReporte> eReporteCollection;
    @JoinColumn(name = "id_usu", referencedColumnName = "id_usu")
    @ManyToOne
    private MUsuario idUsu;

    public MProfesor() {
    }

    public MProfesor(Integer idPro) {
        this.idPro = idPro;
    }

    public MProfesor(Integer idPro, String apaPro, String amaPro, String nomPro, String corPro) {
        this.idPro = idPro;
        this.apaPro = apaPro;
        this.amaPro = amaPro;
        this.nomPro = nomPro;
        this.corPro = corPro;
    }

    public Integer getIdPro() {
        return idPro;
    }

    public void setIdPro(Integer idPro) {
        this.idPro = idPro;
    }

    public String getApaPro() {
        return apaPro;
    }

    public void setApaPro(String apaPro) {
        this.apaPro = apaPro;
    }

    public String getAmaPro() {
        return amaPro;
    }

    public void setAmaPro(String amaPro) {
        this.amaPro = amaPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getCorPro() {
        return corPro;
    }

    public void setCorPro(String corPro) {
        this.corPro = corPro;
    }

    @XmlTransient
    public Collection<EReporte> getEReporteCollection() {
        return eReporteCollection;
    }

    public void setEReporteCollection(Collection<EReporte> eReporteCollection) {
        this.eReporteCollection = eReporteCollection;
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
        hash += (idPro != null ? idPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MProfesor)) {
            return false;
        }
        MProfesor other = (MProfesor) object;
        if ((this.idPro == null && other.idPro != null) || (this.idPro != null && !this.idPro.equals(other.idPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MProfesor[ idPro=" + idPro + " ]";
    }
    
}
