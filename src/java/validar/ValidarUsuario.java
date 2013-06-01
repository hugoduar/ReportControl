/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Usuario;

/**
 *
 * @author Hugo
 */
public class ValidarUsuario {
    Usuario usuario;

    public ValidarUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public boolean esValido(){
        String nicUsu = this.usuario.getNicUsu();
        String conUsu = this.usuario.getConUsu();
        
        boolean esValido = false;
        
        String regexNicUsu = "^[a-zA-Z 0-9]{3,16}+$";

        Pattern patternNicUsu = Pattern.compile(regexNicUsu);
        Matcher matcherNicUsu = patternNicUsu.matcher(nicUsu);
        
        String regexConUsu = "^[a-zA-Z]\\w{3,16}$"; 
        
        Pattern patternConUsu = Pattern.compile(regexNicUsu);
        Matcher matcherConUsu = patternConUsu.matcher(conUsu);
        
        if(matcherNicUsu.matches() && matcherConUsu.matches()){
            esValido = true;
             
        }
        return esValido;
    }
}
