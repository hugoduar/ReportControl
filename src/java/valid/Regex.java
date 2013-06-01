/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hugo
 */
public class Regex {
    String patronstr;

    public Regex(String patronstr) {
        this.patronstr = patronstr;
    }
    public Matcher getMatcher(CharSequence strToCompare){
        return Pattern.compile(patronstr).matcher(strToCompare);
    }
    public Pattern getPatron(String patronstr) {
        return Pattern.compile(patronstr);
    }
    
}
