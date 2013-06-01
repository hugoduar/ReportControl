/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valid;

/**
 *
 * @author Hugo
 */
public class Valid {
    Regex regEx;
    String string;
    
    public static final Regex MAIL = new Regex("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
    public static final Regex USERNAME = new Regex("^[a-z\\d_]{3,15}$");
    public static final Regex PASSWORD = new Regex("^[a-zA-Z0-9]\\w{5,20}$");
    public static final Regex NOMBRE = new Regex("^[a-zA-Z ñ]\\w{3,60}$");
    
    public Valid(String string) {
        this.string = string;
    }
    
    public String getString() {
        return string;
    }
    public boolean isValid(){
        return regEx.getMatcher(getString()).find();
    }
    public Regex getRegEx() {
        return regEx;
    }

    public void setRegEx(Regex regEx) {
        this.regEx = regEx;
    }


    public void setString(String stringValidar) {
        this.string = stringValidar;
    }
    public  boolean mailIsValid(){
        setRegEx(MAIL);
        return isValid();
    }
    public boolean userNameIsValid(){
        setRegEx(USERNAME);
        return isValid();
    }
    public boolean passwordIsValid(){
        setRegEx(PASSWORD);
        return isValid();
    }
    public boolean nombreIsValid(){
        setRegEx(NOMBRE);
        return isValid();
    }
}
