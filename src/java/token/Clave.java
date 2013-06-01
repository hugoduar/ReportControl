/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 *
 * @author Hugo
 */
public class Clave {
    public int combinacion;
    public int clave;

    public Clave() {
    }
    
    public Clave(int combinacion) {
        
        this.combinacion = combinacion;
    }

    public void setCombinacion(int combinacion) {
        this.combinacion = combinacion;
    }

    public int getClave() {
        return clave;
    }

    public int getCombinacion() {
        return combinacion;
    }
    
    public int generarClave(){
        char clave [] = new char[5];
        int combinacion = getCombinacion();
        String combinacionStr = String.valueOf(combinacion);
        if(combinacion>99999){
            return 0;
        }else{
            int claveRes;
            String claveResStr = "";
            for(int i=0; i<5 ; i++){
                clave[i] = combinacionStr.charAt(i);
                switch (clave[i]){
                    case '1':
                      claveResStr += '2';
                      break;
                    case '2':
                      claveResStr += '3';
                      break;
                    case '3':
                      claveResStr += '5';
                      break;
                    case '4':
                      claveResStr += '1';
                      break;
                    case '5':
                      claveResStr += '4';
                      break;  
                }
            }
            claveRes = Integer.parseInt(claveResStr);
            return claveRes;
        }
    }
    
    public boolean comprobarClave(int Clave){
        if(generarClave()==Clave){
            return true;
        }else{
            return false;
        }
        
    }
    
    public String GenerarCombinacion(){
        int a1;
        int a2;
        int a3;
        int a4;
        int a5;
        a1 = (int) Math.floor(Math.random()*5+1);
        a2 = (int) Math.floor(Math.random()*5+1);
        a3 = (int) Math.floor(Math.random()*5+1);
        a4 = (int) Math.floor(Math.random()*5+1);
        a5 = (int) Math.floor(Math.random()*5+1);
        return String.valueOf(a1)+String.valueOf(a2)+String.valueOf(a3)+String.valueOf(a4)+String.valueOf(a5);
    }
    
    
}
