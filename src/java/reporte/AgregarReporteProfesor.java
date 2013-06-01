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
public class AgregarReporteProfesor extends HttpServlet {

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
        String des_rep = request.getParameter("txtDescReporte");
        String nom_equ = request.getParameter("inputNumero");
        String tfalla = request.getParameter("optionFalla");
        String des_fla = request.getParameter("inputDesFalla");
        String nom_asi = request.getParameter("inputAsignatura");
        String nic_pro = request.getParameter("inputNickProfesor");
        String pas_pro = request.getParameter("inputPasswordProfesor");
        String nusuario = (String) request.getSession().getAttribute("nickUser");
        PrintWriter out = response.getWriter();
        try {
            if(tfalla.equals("software")){
                    Bd.addReporteNuevoProfesor(nusuario,nom_asi, des_rep, nom_equ, des_fla, 0);
                    response.sendRedirect("profesor/reporte-pro.jsp");
                }else if(tfalla.equals("hardware")){
                    Bd.addReporteNuevoProfesor(nusuario,nom_asi, des_rep, nom_equ, des_fla, 1);
                    response.sendRedirect("profesor/reporte-pro.jsp");
                }else{
                    response.sendRedirect("profesor/reporte-pro.jsp");
                }
        } catch (Exception ex) {
            Logger.getLogger(AgregarReporteProfesor.class.getName()).log(Level.SEVERE, null, ex);
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
