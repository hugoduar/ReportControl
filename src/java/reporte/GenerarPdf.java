/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

import bd.Bd;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import datos.JoinBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hugo
 */
public class GenerarPdf extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
            try{
            Connection con = Conexion.getConexion();
            response.setContentType("application/pdf"); // Code 1
            Document document = new Document();
            PdfWriter.getInstance(document, 
            response.getOutputStream()); // Code 2
            document.open();
                    
                    // Code 3
                    PdfPTable tblRep = new PdfPTable(6);		
                    
                    tblRep.addCell("Id del reporte");
                    tblRep.addCell("Descripcion del reporte");
                    tblRep.addCell("Laboratorio");
                    tblRep.addCell("Máquina");
                    tblRep.addCell("Fecha");
                    tblRep.addCell("Estado");
                    
                    
               
               HttpSession session = request.getSession();
               System.out.println((String)session.getAttribute("fecIni"));
               ResultSet rs = JoinBD.getReportesPorFecha(con, (String)session.getAttribute("fecIni"), (String)session.getAttribute("fecFin"));
                int idRep;
                String descRep;
                String labEqui;
                String nomEqui;
                String fecRep;
                String desEst;
                int idProf;
                String prof;
                int idAlu;
                String alu;

                String row;
                while(rs.next()){
                    idRep = rs.getInt("id_rep");
                    descRep = rs.getString("des_rep");
                    labEqui = rs.getString("nom_lab");
                    nomEqui = rs.getString("nom_equ");
                    fecRep = rs.getString("fec_rep");
                    desEst = rs.getString("des_ere");
                    idProf = rs.getInt("id_pro");
                    idAlu = rs.getInt("id_alu");

                    tblRep.addCell(String.valueOf(idRep));
                    tblRep.addCell(descRep);
                    tblRep.addCell(labEqui);
                    tblRep.addCell(nomEqui);
                    tblRep.addCell(fecRep);
                    tblRep.addCell(desEst);
                    
                }
                document.add(tblRep);
                document.close(); 
            } catch (Exception ex) {
            Logger.getLogger(GenerarPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
