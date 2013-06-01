/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Equipo {
    String nomEqu;
    int idLab;

    public Equipo() {
    }

    public Equipo(String nomEqu, int idLab) {
        this.nomEqu = nomEqu;
        this.idLab = idLab;
    }

    public String getNomEqu() {
        return nomEqu;
    }

    public void setNomEqu(String nomEqu) {
        this.nomEqu = nomEqu;
    }

    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }
    
    
}
