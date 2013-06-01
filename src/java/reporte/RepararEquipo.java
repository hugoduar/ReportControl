/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

import bd.Bd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hugo
 */
public class RepararEquipo extends HttpServlet {

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
        response.sendRedirect("404.html");
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
        processRequest(request, response);
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
        String idRep = request.getParameter("inputNumero");
        String txtDesRep = request.getParameter("txtDescRep");
        PrintWriter out = response.getWriter();
        out.println(idRep+txtDesRep);
        try {
            Bd.setEstadoReporte(2, Integer.parseInt(idRep));
            int idTec=Bd.getIdTecnico((String)request.getSession().getAttribute("nickUser"));
            String nomEq=Bd.getNombreEquipo(Integer.parseInt(idRep));
            Bd.addMReparacion(idTec, Integer.parseInt(idRep), txtDesRep, nomEq);
            response.sendRedirect("tecnico/reporte-tec.jsp");
        } catch (Exception ex) {
            Logger.getLogger(RepararEquipo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
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
