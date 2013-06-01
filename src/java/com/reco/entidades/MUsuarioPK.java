/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hugo
 */
@Embeddable
public class MUsuarioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_usu")
    private int idUsu;
    @Basic(optional = false)
    @Column(name = "nic_usu")
    private String nicUsu;

    public MUsuarioPK() {
    }

    public MUsuarioPK(int idUsu, String nicUsu) {
        this.idUsu = idUsu;
        this.nicUsu = nicUsu;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getNicUsu() {
        return nicUsu;
    }

    public void setNicUsu(String nicUsu) {
        this.nicUsu = nicUsu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsu;
        hash += (nicUsu != null ? nicUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MUsuarioPK)) {
            return false;
        }
        MUsuarioPK other = (MUsuarioPK) object;
        if (this.idUsu != other.idUsu) {
            return false;
        }
        if ((this.nicUsu == null && other.nicUsu != null) || (this.nicUsu != null && !this.nicUsu.equals(other.nicUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reco.entidades.MUsuarioPK[ idUsu=" + idUsu + ", nicUsu=" + nicUsu + " ]";
    }
    
}
