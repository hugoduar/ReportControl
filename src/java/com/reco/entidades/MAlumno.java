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
@Table(name = "MAlumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MAlumno.findAll", query = "SELECT m FROM MAlumno m"),
    @NamedQuery(name = "MAlumno.findByIdAlu", query = "SELECT m FROM MAlumno m WHERE m.idAlu = :idAlu"),
    @NamedQuery(name = "MAlumno.findByApaAlu", query = "SELECT m FROM MAlumno m WHERE m.apaAlu = :apaAlu"),
    @NamedQuery(name = "MAlumno.findByAmaAlu", query = "SELECT m FROM MAlumno m WHERE m.amaAlu = :amaAlu"),
    @NamedQuery(name = "MAlumno.findByNomAlu", query = "SELECT m FROM MAlumno m WHERE m.nomAlu = :nomAlu"),
    @NamedQuery(name = "MAlumno.findByBolAlu", query = "SELECT m FROM MAlumno m WHERE m.bolAlu = :bolAlu"),
    @NamedQuery(name = "MAlumno.findByGpoAlu", query = "SELECT m FROM MAlumno m WHERE m.gpoAlu = :gpoAlu"),
    @NamedQuery(name = "MAlumno.findByCorUsu", query = "SELECT m FROM MAlumno m WHERE m.corUsu = :corUsu")})
public class MAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alu")
    private Integer idAlu;
    @Basic(optional = false)
    @Column(name = "apa_alu")
    private String apaAlu;
    @Basic(optional = false)
    @Column(name = "ama_alu")
    private String amaAlu;
    @Basic(optional = false)
    @Column(name = "nom_alu")
    private String nomAlu;
    @Basic(optional = false)
    @Column(name = "bol_alu")
    private int bolAlu;
    @Basic(optional = false)
    @Column(name = "gpo_alu")
    private String gpoAlu;
    @Basic(optional = false)
    @Column(name = "cor_usu")
    private String corUsu;
    @OneToMany(mappedBy = "idAlu")
    private Collection<EReporte> eReporteCollection;
    @JoinColumn(name = "id_usu", referencedColumnName = "id_usu")
    @ManyToOne
    private MUsuario idUsu;

    public MAlumno() {
    }

    public MAlumno(Integer idAlu) {
        this.idAlu = idAlu;
    }

    public MAlumno(Integer idAlu, String apaAlu, String amaAlu, String nomAlu, int bolAlu, String gpoAlu, String corUsu) {
        this.idAlu = idAlu;
        this.apaAlu = apaAlu;
        this.amaAlu = amaAlu;
        this.nomAlu = nomAlu;
        this.bolAlu = bolAlu;
        this.gpoAlu = gpoAlu;
        this.corUsu = corUsu;
    }

    public Integer getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(Integer idAlu) {
        this.idAlu = idAlu;
    }

    public String getApaAlu() {
        return apaAlu;
    }

    public void setApaAlu(String apaAlu) {
        this.apaAlu = apaAlu;
    }

    public String getAmaAlu() {
        return amaAlu;
    }

    public void setAmaAlu(String amaAlu) {
        this.amaAlu = amaAlu;
    }

    public String getNomAlu() {
        return nomAlu;
    }

    public void setNomAlu(String nomAlu) {
        this.nomAlu = nomAlu;
    }

    public int getBolAlu() {
        return bolAlu;
    }

    public void setBolAlu(int bolAlu) {
        this.bolAlu = bolAlu;
    }

    public String getGpoAlu() {
        return gpoAlu;
    }

    public void setGpoAlu(String gpoAlu) {
        this.gpoAlu = gpoAlu;
    }

    public String getCorUsu() {
        return corUsu;
    }

    public void setCorUsu(String corUsu) {
        this.corUsu = corUsu;
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
        hash += (idAlu != null ? idAlu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MAlumno)) {
            return false;
        }
        MAlumno other = (MAlumno) object;
        if ((this.idAlu == null && other.idAlu != null) || (this.idAlu != null && !this.idAlu.equals(other.idAlu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MAlumno[ idAlu=" + idAlu + " ]";
    }
    
}
