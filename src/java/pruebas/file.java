/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author Hugo
 */
public class file {
    private File archivo;
public void Archivo()
{
    archivo = new File("miarchivo.txt");//aqui escribimos el nombre del archivo
//y su formato, no importa la extension que uses siempre sera un
//archivo de texto por ejemplo si le pones .mp3 y lo abres con bloc
//de notas veras el contenido pero si lo reproduces fallara.

try
{
PrintWriter grabador = new PrintWriter(archivo);//aqui creamos
//el objeto que permite grabar en el archivo

grabador.println("Hola");//aqui simplemente mandamos la cadena
//a grabar podemos usar varias veces la funcion para grabar 
//varias lineas en el archivo
grabador.println("¿Como estas?");

grabador.close();//es muy importante cerrar el archivo ya que
//sino lo haces no se guardan los cambios
}
catch(Exception e)
{

}
}
}
