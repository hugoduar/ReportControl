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
@Table(name = "CTipoFalla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CTipoFalla.findAll", query = "SELECT c FROM CTipoFalla c"),
    @NamedQuery(name = "CTipoFalla.findByIdTfa", query = "SELECT c FROM CTipoFalla c WHERE c.idTfa = :idTfa"),
    @NamedQuery(name = "CTipoFalla.findByDesTfa", query = "SELECT c FROM CTipoFalla c WHERE c.desTfa = :desTfa"),
    @NamedQuery(name = "CTipoFalla.findByTipFal", query = "SELECT c FROM CTipoFalla c WHERE c.tipFal = :tipFal")})
public class CTipoFalla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tfa")
    private Integer idTfa;
    @Basic(optional = false)
    @Column(name = "des_tfa")
    private String desTfa;
    @Column(name = "tip_fal")
    private Short tipFal;
    @OneToMany(mappedBy = "idTfa")
    private Collection<MReporte> mReporteCollection;

    public CTipoFalla() {
    }

    public CTipoFalla(Integer idTfa) {
        this.idTfa = idTfa;
    }

    public CTipoFalla(Integer idTfa, String desTfa) {
        this.idTfa = idTfa;
        this.desTfa = desTfa;
    }

    public Integer getIdTfa() {
        return idTfa;
    }

    public void setIdTfa(Integer idTfa) {
        this.idTfa = idTfa;
    }

    public String getDesTfa() {
        return desTfa;
    }

    public void setDesTfa(String desTfa) {
        this.desTfa = desTfa;
    }

    public Short getTipFal() {
        return tipFal;
    }

    public void setTipFal(Short tipFal) {
        this.tipFal = tipFal;
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
        hash += (idTfa != null ? idTfa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CTipoFalla)) {
            return false;
        }
        CTipoFalla other = (CTipoFalla) object;
        if ((this.idTfa == null && other.idTfa != null) || (this.idTfa != null && !this.idTfa.equals(other.idTfa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.CTipoFalla[ idTfa=" + idTfa + " ]";
    }
    
}
