/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class PermisoUsuario {
    int idTus;
    int idPer;

    public PermisoUsuario() {
    }

    public PermisoUsuario(int idTus, int idPer) {
        this.idTus = idTus;
        this.idPer = idPer;
    }

    public int getIdTus() {
        return idTus;
    }

    public void setIdTus(int idTus) {
        this.idTus = idTus;
    }

    public int getIdPer() {
        return idPer;
    }

    public void setIdPer(int idPer) {
        this.idPer = idPer;
    }
   
    
}
