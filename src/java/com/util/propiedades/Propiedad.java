
package com.util.propiedades;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class Propiedad {
    
    public static String getPropiedad(String propiedad){
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            if(!prop.isEmpty()){
                return prop.getProperty(propiedad);
            }else{
                return "lols";
            }
        } catch (IOException ex) {
            return "lols";
        }
    }
    
}
