/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.usuario;

import bd.Bd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
public class EliminarUsuario extends HttpServlet {

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
        response.sendRedirect("inicio.jsp");
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
        try {
            String nicUsu = request.getParameter("boton");
            String tip =  request.getParameter("inpTip");
            int idUsu = Bd.getIdUsuario(nicUsu);
            int idTusu = 0;
            ResultSet rs;
            if(tip.equals("1")){
                rs = Bd.getDatosAlumno(nicUsu);
                while(rs.next()){
                    idTusu = rs.getInt("id_alu");
                }
                Bd.EliminarAlumno(idTusu);
                Bd.EliminarUsuario(nicUsu);
            }else if(tip.equals("2")){
                rs = Bd.getDatosProfesor(nicUsu);
                while(rs.next()){
                    idTusu = rs.getInt("id_pro");
                }
                Bd.EliminarProfesor(idTusu);
                Bd.EliminarUsuario(nicUsu);
            }else if(tip.equals("3")){
                rs = Bd.getDatosTecnico(nicUsu);
                while(rs.next()){
                    idTusu = rs.getInt("id_tec");
                }
                Bd.EliminarTecnico(idTusu);
                Bd.EliminarUsuario(nicUsu);
            }
            response.sendRedirect("admin/eliminar-usuario.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
