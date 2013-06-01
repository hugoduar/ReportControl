/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Usuario {
    int idUsu;
    String nicUsu;
    String conUsu;
    int idTus;

    public Usuario() {
    }

    public Usuario(int idUsu, String nicUsu, String conUsu, int idTus) {
        this.idUsu = idUsu;
        this.nicUsu = nicUsu;
        this.conUsu = conUsu;
        this.idTus = idTus;
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

    public String getConUsu() {
        return conUsu;
    }

    public void setConUsu(String conUsu) {
        this.conUsu = conUsu;
    }

    public int getIdTus() {
        return idTus;
    }

    public void setIdTus(int idTus) {
        this.idTus = idTus;
    }
    
    
}
