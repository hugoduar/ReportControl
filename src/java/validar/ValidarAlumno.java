/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Alumno;

/**
 *
 * @author Hugo
 */
public class ValidarAlumno {
    Alumno alumno;

    public ValidarAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public boolean esValido(){
        String nomAlu = this.alumno.getNomAlu();
        String apaAlu = this.alumno.getApaAlu();
        String amaAlu = this.alumno.getAmaAlu();
        String corAlu = this.alumno.getCorAlu();
        String bolAlu = String.valueOf(this.alumno.getBolAlu());
        
        boolean esValido = false;
        String regexEmail = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(corAlu);
        
        String regexNombre = "^[a-zA-Z]+((\\s|\\-)[a-zA-Z]+)?$";

        Pattern patternNomAlu = Pattern.compile(regexNombre);
        Matcher matcherNomAlu = patternNomAlu.matcher(nomAlu);
        
        Pattern patternApaAlu = Pattern.compile(regexNombre);
        Matcher matcherApaAlu = patternApaAlu.matcher(apaAlu);
        
        Pattern patternAmaAlu = Pattern.compile(regexNombre);
        Matcher matcherAmaAlu = patternApaAlu.matcher(amaAlu);
        
        String regexBoleta = "^[0-9]{10}+$";
        
        Pattern patternbolAlu = Pattern.compile(regexBoleta);
        Matcher matcherbolAlu = patternbolAlu.matcher(bolAlu);
        
        if(matcherEmail.matches() && matcherNomAlu.matches() && matcherApaAlu.matches() && matcherAmaAlu.matches() && matcherbolAlu.matches()){
            esValido = true;
        }
        return esValido;
    }
    
}
