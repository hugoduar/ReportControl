/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class EReporte {
    int idEce;
    int idPro;
    int idAlu;

    public EReporte() {
    }

    public EReporte(int idEce, int idPro) {
        this.idEce = idEce;
        this.idPro = idPro;
    }

    public EReporte(int idEce, int idPro, int idAlu) {
        this.idEce = idEce;
        this.idPro = idPro;
        this.idAlu = idAlu;
    }

    public int getIdEce() {
        return idEce;
    }

    public void setIdEce(int idEce) {
        this.idEce = idEce;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(int idAlu) {
        this.idAlu = idAlu;
    }
    
    
}
