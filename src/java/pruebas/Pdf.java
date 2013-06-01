/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import bd.Bd;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import datos.AlumnoBD;
import datos.PermisoBD;
import datos.ReporteBD;
import datos.TipoUsuarioBD;
import datos.UsuarioBD;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modelo.Alumno;
import modelo.Permiso;
import modelo.Reporte;
import modelo.TipoUsuario;
import modelo.Usuario;
import validar.ValidarAlumno;
import validar.ValidarUsuario;
/**
 *
 * @author Hugo
 */
public class Pdf {

public static String GenerarPdfReportes()
{
        try {
            OutputStream file = new FileOutputStream(new File("web/analista/reportes.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Lol"));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return "analista/reportes.pdf";
}
        public static void main(String args[]) throws Exception{
            Connection con  = Conexion.getConexion();
            /*ReporteBD repBD = new ReporteBD();
            List<Reporte> lstRep = repBD.getPorMasNumeroDeReportes(con);
            Iterator itrLstRep = lstRep.iterator();
            while(itrLstRep.hasNext()){
                Reporte rep  = (Reporte) itrLstRep.next();
                System.out.println(rep.getNomEqu());
            }*/
            //Bd.addReporteNuevoProfesor("prof","Seguridad", "No funciona el teclado", "38", "Teclado", 1);
            ResultSet rs = ReporteBD.getReportesPorIdLab(con, 2);
            while(rs.next()){
                System.out.println(rs.getInt("id_rep")+rs.getString("des_rep")+rs.getString("nom_equ")+rs.getString("fec_rep")+rs.getString("des_ere"));
            }
            //System.out.println(ReporteBD.getNombreCompletoAlumnoPorIdReporte(7));
            con.close();
            
        }
    
}
