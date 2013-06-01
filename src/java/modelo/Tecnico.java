/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Tecnico {
    int idTec;
    int idUsu;
    String apaTec;
    String amaTec;
    String nomTec;
    String corTec;

    public Tecnico() {
    }

    public Tecnico(int idTec, int idUsu, String apaTec, String amaTec, String nomTec, String corTec) {
        this.idTec = idTec;
        this.idUsu = idUsu;
        this.apaTec = apaTec;
        this.amaTec = amaTec;
        this.nomTec = nomTec;
        this.corTec = corTec;
    }

    public int getIdTec() {
        return idTec;
    }

    public void setIdTec(int idTec) {
        this.idTec = idTec;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
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
    
    
}
