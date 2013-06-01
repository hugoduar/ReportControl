/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hugo
 */
@Entity
@Table(name = "vprofesor_ereporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VprofesorEreporte.findAll", query = "SELECT v FROM VprofesorEreporte v"),
    @NamedQuery(name = "VprofesorEreporte.findByIdEce", query = "SELECT v FROM VprofesorEreporte v WHERE v.idEce = :idEce"),
    @NamedQuery(name = "VprofesorEreporte.findByNomPro", query = "SELECT v FROM VprofesorEreporte v WHERE v.nomPro = :nomPro")})
public class VprofesorEreporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_ece")
    private int idEce;
    @Basic(optional = false)
    @Column(name = "nom_pro")
    private String nomPro;

    public VprofesorEreporte() {
    }

    public int getIdEce() {
        return idEce;
    }

    public void setIdEce(int idEce) {
        this.idEce = idEce;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }
    
}
