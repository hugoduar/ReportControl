/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 *
 * @author Hugo
 */
public class Token {
    public String generarNumero(){
        int a1;
        int a2;
        int a3;
        int a4;
        int a5;
        a1 = (int) Math.random()*9;
        a2 = (int) Math.random()*9;
        a3 = (int) Math.random()*9;
        a4 = (int) Math.random()*9;
        a5 = (int) Math.random()*9;
        return String.valueOf(a1)+String.valueOf(a2)+String.valueOf(a3)+String.valueOf(a4)+String.valueOf(a5);
    }
    public boolean comprobarNumero(int[] n){
        boolean ret = false;
        int [] nc = new int[10];
        nc[0] = 8;
        nc[1] = 2;
        nc[2] = 4;
        nc[3] = 5;
        nc[4] = 7;
        nc[5] = 1;
        nc[6] = 3;
        nc[7] = 2;
        nc[8] = 9;
        nc[9] = 0;
        
        if(n.length>=10){
            return ret; 
        }else{
            for(int i=0; i<n.length; i++){
                if(n[i]==nc[i]){
                   ret = true; 
                }else{
                    ret = false;
                    return false;
                }
            }
        }
        return ret;
        
    }
    
}
