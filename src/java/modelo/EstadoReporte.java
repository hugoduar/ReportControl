/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class EstadoReporte {
    int idEre;
    String desEre;

    public EstadoReporte() {
    }

    public EstadoReporte(int idEre, String desEre) {
        this.idEre = idEre;
        this.desEre = desEre;
    }

    public int getIdEre() {
        return idEre;
    }

    public void setIdEre(int idEre) {
        this.idEre = idEre;
    }

    public String getDesEre() {
        return desEre;
    }

    public void setDesEre(String desEre) {
        this.desEre = desEre;
    }
    
}
