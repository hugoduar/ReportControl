/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Permiso {
    int idPer;
    String desPer;

    public Permiso() {
    }

    public Permiso(int idPer, String desPer) {
        this.idPer = idPer;
        this.desPer = desPer;
    }

    public int getIdPer() {
        return idPer;
    }

    public void setIdPer(int idPer) {
        this.idPer = idPer;
    }

    public String getDesPer() {
        return desPer;
    }

    public void setDesPer(String desPer) {
        this.desPer = desPer;
    }
    
}
