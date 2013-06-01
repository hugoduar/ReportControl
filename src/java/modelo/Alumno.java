/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Hugo
 */
public class Alumno {
    int idAlu;
    int idUsu;
    String apaAlu;
    String amaAlu;
    String nomAlu;
    int bolAlu;
    String gpoAlu;
    String corAlu;

    public Alumno() {
    }

    public Alumno(int idAlu, int idUsu, String apaAlu, String amaAlu, String nomAlu, int bolAlu, String gpoAlu, String corAlu) {
        this.idAlu = idAlu;
        this.idUsu = idUsu;
        this.apaAlu = apaAlu;
        this.amaAlu = amaAlu;
        this.nomAlu = nomAlu;
        this.bolAlu = bolAlu;
        this.gpoAlu = gpoAlu;
        this.corAlu = corAlu;
    }

    public int getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(int idAlu) {
        this.idAlu = idAlu;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public String getApaAlu() {
        return apaAlu;
    }

    public void setApaAlu(String apaAlu) {
        this.apaAlu = apaAlu;
    }

    public String getAmaAlu() {
        return amaAlu;
    }

    public void setAmaAlu(String amaAlu) {
        this.amaAlu = amaAlu;
    }

    public String getNomAlu() {
        return nomAlu;
    }

    public void setNomAlu(String nomAlu) {
        this.nomAlu = nomAlu;
    }

    public int getBolAlu() {
        return bolAlu;
    }

    public void setBolAlu(int bolAlu) {
        this.bolAlu = bolAlu;
    }

    public String getGpoAlu() {
        return gpoAlu;
    }

    public void setGpoAlu(String gpoAlu) {
        this.gpoAlu = gpoAlu;
    }

    public String getCorAlu() {
        return corAlu;
    }

    public void setCorAlu(String corAlu) {
        this.corAlu = corAlu;
    }
    
    
}
