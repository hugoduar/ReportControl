/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Reparacion {
    int idRci;
    String desReq;
    String fecRep;
    int idRep;
    String nomEqu;
    int idTec;

    public Reparacion() {
    }

    public Reparacion(int idRci, String desReq, String fecReq, int idRep, String nomEqu, int idTec) {
        this.idRci = idRci;
        this.desReq = desReq;
        this.fecRep = fecReq;
        this.idRep = idRep;
        this.nomEqu = nomEqu;
        this.idTec = idTec;
    }

    public int getIdRci() {
        return idRci;
    }

    public void setIdRci(int idRci) {
        this.idRci = idRci;
    }

    public String getDesReq() {
        return desReq;
    }

    public void setDesReq(String desReq) {
        this.desReq = desReq;
    }

    public String getFecRep() {
        return fecRep;
    }

    public void setFecRep(String fecRep) {
        this.fecRep = fecRep;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(String nomEqu) {
        this.nomEqu = nomEqu;
    }

    public int getIdTec() {
        return idTec;
    }

    public void setIdTec(int idTec) {
        this.idTec = idTec;
    }
    
}
