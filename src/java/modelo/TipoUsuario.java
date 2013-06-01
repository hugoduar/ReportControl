/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class TipoUsuario {
    private int idTus;
    private int tiemSem;
    private String desTus;

    public TipoUsuario(int idTus, int tiemSem, String desTus) {
        this.idTus = idTus;
        this.tiemSem = tiemSem;
        this.desTus = desTus;
    }

    public TipoUsuario() {
    }

    public int getIdTus() {
        return idTus;
    }

    public void setIdTus(int idTus) {
        this.idTus = idTus;
    }

    public int getTiemSem() {
        return tiemSem;
    }

    public void setTiemSem(int tiemSem) {
        this.tiemSem = tiemSem;
    }

    public String getDesTus() {
        return desTus;
    }

    public void setDesTus(String desTus) {
        this.desTus = desTus;
    }
    
}
