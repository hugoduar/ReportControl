/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Profesor {
    int idPro;
    int idUsu;
    String apaPro;
    String amaPro;
    String nomPro;
    String corPro;

    public Profesor() {
    }

    public Profesor(int idPro, int idUsu, String apaPro, String amaPro, String nomPro, String corPro) {
        this.idPro = idPro;
        this.idUsu = idUsu;
        this.apaPro = apaPro;
        this.amaPro = amaPro;
        this.nomPro = nomPro;
        this.corPro = corPro;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getApaPro() {
        return apaPro;
    }

    public void setApaPro(String apaPro) {
        this.apaPro = apaPro;
    }

    public String getAmaPro() {
        return amaPro;
    }

    public void setAmaPro(String amaPro) {
        this.amaPro = amaPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getCorPro() {
        return corPro;
    }

    public void setCorPro(String corPro) {
        this.corPro = corPro;
    }
    
    
}
