/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador.sesion;

import bd.Bd;
import conexion.Conexion;
import datos.TipoUsuarioBD;
import datos.UsuarioBD;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.TipoUsuario;
import modelo.Usuario;

/**
 *
 * @author Hugo
 */
public class Login extends HttpServlet {

    @Override
    public ServletConfig getServletConfig() {
        return super.getServletConfig();
    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    
    
    
    public boolean existeUsuario(String nicUsu, String conUsu) throws Exception{
        Connection con  = Conexion.getConexion();
        UsuarioBD usuBD = new UsuarioBD();
        Usuario usu = new Usuario();
        usu = usuBD.getPorNicUsuANDConUsu(con, nicUsu, conUsu);
        boolean existe = false;
        if(usu.getIdUsu()!=0){
            existe = true;
        } 
        con.close();
        return existe;
    }
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
    public void init(ServletConfig config) throws ServletException {
        
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
        response.sendRedirect("404.html");     
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
            PrintWriter out = response.getWriter();
            String nombreUsuario = request.getParameter("inpUsuario");
            String password = request.getParameter("inpPassword");

            boolean entrada = existeUsuario(nombreUsuario, password);
            Usuario usu = new Usuario();
            UsuarioBD usuBD = new UsuarioBD();
            Connection con  = Conexion.getConexion();
            
            if(entrada){
                usu = usuBD.getPorNicUsuANDConUsu(con, nombreUsuario, password);
                TipoUsuario tipoUsuario = new TipoUsuario();
                TipoUsuarioBD tipoUsuarioBD = new TipoUsuarioBD();
                tipoUsuario = tipoUsuarioBD.getPorIdTus(con, usu.getIdTus());
                String rol = tipoUsuario.getDesTus();
                int idUser = usu.getIdUsu();
                System.out.println("Entro el usuario: "+usu.getNicUsu());
                if(rol.equals("Administrador")){
                    request.getSession().setAttribute("rol", rol);
                    request.getSession().setAttribute("idUser", idUser);
                    response.sendRedirect("admin/inicio.jsp");
                    
                }else{
                    request.getSession().setAttribute("rol", rol.toLowerCase());
                    request.getSession().setAttribute("idUser", idUser);
                    request.getSession().setAttribute("nickUser", nombreUsuario);
                    response.sendRedirect(rol.toLowerCase()+"/inicio.jsp");
                } 
            }
            // == == =  Si el nombre de usuario y contrasenia no son aceptados = = = 
            else{
                HttpSession session = request.getSession();
                ServletContext context = session.getServletContext();
                request.setAttribute("rechazado", "true");
                request.setAttribute("msj", "Datos Invalidos");
                context.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    
}
