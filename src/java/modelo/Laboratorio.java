/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Laboratorio {
    int idLab;
    String nomLab;

    public Laboratorio() {
    }

    public Laboratorio(int idLab, String nomLab) {
        this.idLab = idLab;
        this.nomLab = nomLab;
    }

    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public String getNomLab() {
        return nomLab;
    }

    public void setNomLab(String nomLab) {
        this.nomLab = nomLab;
    }
    
}
