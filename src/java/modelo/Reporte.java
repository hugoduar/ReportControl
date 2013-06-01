/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Reporte {
    int idRep;
    String desRep;
    String fecRep;
    String nomAsi;
    int idEce;
    int idEre;
    int idTfa;
    String nomEqu;

    public Reporte() {
    }

    public Reporte(int idRep, String desRep, String fecRep, String nomAsi, int idEce, int idEre, int idTfa, String nomEqu) {
        this.idRep = idRep;
        this.desRep = desRep;
        this.fecRep = fecRep;
        this.nomAsi = nomAsi;
        this.idEce = idEce;
        this.idEre = idEre;
        this.idTfa = idTfa;
        this.nomEqu = nomEqu;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getDesRep() {
        return desRep;
    }

    public void setDesRep(String desRep) {
        this.desRep = desRep;
    }

    public String getFecRep() {
        return fecRep;
    }

    public void setFecRep(String fecRep) {
        this.fecRep = fecRep;
    }

    public String getNomAsi() {
        return nomAsi;
    }

    public void setNomAsi(String nomAsi) {
        this.nomAsi = nomAsi;
    }

    public int getIdEce() {
        return idEce;
    }

    public void setIdEce(int idEce) {
        this.idEce = idEce;
    }

    public int getIdEre() {
        return idEre;
    }

    public void setIdEre(int idEre) {
        this.idEre = idEre;
    }

    public int getIdTfa() {
        return idTfa;
    }

    public void setIdTfa(int idTfa) {
        this.idTfa = idTfa;
    }

    public String getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(String nomEqu) {
        this.nomEqu = nomEqu;
    }
    
    
    
}
