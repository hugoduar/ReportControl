/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Alumno;
import modelo.Profesor;

/**
 *
 * @author Hugo
 */
public class ValidarProfesor {
    Profesor profesor;

    public ValidarProfesor(Profesor alumno) {
        this.profesor = alumno;
    }

    public Profesor getAlumno() {
        return profesor;
    }

    public void setAlumno(Profesor alumno) {
        this.profesor = alumno;
    }
    
    public boolean esValido(){
        String nomPro = this.profesor.getNomPro();
        String apaPro = this.profesor.getApaPro();
        String amaPro = this.profesor.getAmaPro();
        String corPro = this.profesor.getCorPro();
        
        boolean esValido = false;
        String regexEmail = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Matcher matcherEmail = patternEmail.matcher(corPro);
        
        String regexNombre = "^[a-zA-Z]+$";

        Pattern patternNomAlu = Pattern.compile(regexNombre);
        Matcher matcherNomAlu = patternNomAlu.matcher(nomPro);
        
        Pattern patternApaAlu = Pattern.compile(regexNombre);
        Matcher matcherApaAlu = patternApaAlu.matcher(apaPro);
        
        Pattern patternAmaAlu = Pattern.compile(regexNombre);
        Matcher matcherAmaAlu = patternApaAlu.matcher(amaPro);
        

        
        if(matcherEmail.matches() && matcherNomAlu.matches() && matcherApaAlu.matches() && matcherAmaAlu.matches()){
            esValido = true;
        }
        return esValido;
    }
}
