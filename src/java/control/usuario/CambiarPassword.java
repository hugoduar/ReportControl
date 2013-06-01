/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.usuario;

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
public class CambiarPassword extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nicUsu = (String) request.getSession().getAttribute("nickUser");
        String pass = request.getParameter("inputPass");
        String passNew = request.getParameter("inputPassNew");
        String passNewRep = request.getParameter("inputPassNewRep");
        PrintWriter out =  response.getWriter();
        String Json  = "";
        String msj = null;
        try {
            if(!passNew.equals("")){
                if(passNew.equals(passNewRep)){
                    try {
                        if(Bd.getEntrada(nicUsu, pass)){
                            try {
                                Bd.setPassword(nicUsu, passNew);
                                request.getSession().invalidate();
                                msj = "Password cambiada";
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }else{
                            msj = "Password Incorrecta ";
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    msj  = "Password diferentes";
                }
            }else{
                msj  = "Error en la contraseña" ;
            }
            Json = "[{\"mensaje\":\""+msj+"\"}]";
            out.println(Json);
            
        } catch (SQLException ex) {
            Logger.getLogger(CambiarPassword.class.getName()).log(Level.SEVERE, null, ex);
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
