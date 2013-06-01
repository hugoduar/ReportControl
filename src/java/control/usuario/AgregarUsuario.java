/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.usuario;

import bd.Bd;
import conexion.Conexion;
import datos.AlumnoBD;
import datos.TipoUsuarioBD;
import datos.UsuarioBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Profesor;
import modelo.TipoUsuario;
import modelo.Usuario;
import validar.ValidarAlumno;
import validar.ValidarProfesor;
import validar.ValidarUsuario;

/**
 *
 * @author Alumnos
 */
public class AgregarUsuario extends HttpServlet {

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
            PrintWriter out = response.getWriter();
            String nic_usu = request.getParameter("inputNombreUsuario");
            String con_usu = request.getParameter("inputPassword");
            
            
            String cor_usu = request.getParameter("inputCorreo");
            String nom_usu = request.getParameter("inputNombre");
            String apa_usu = request.getParameter("inputPaterno");
            String ama_usu = request.getParameter("inputMaterno");
            String tip_usu = request.getParameter("selRolUsuario");
            String gpo_alu = request.getParameter("inputGrupo");
            String bol_alu = request.getParameter("inputBoleta");
            
            try{
            Connection con  = Conexion.getConexion();

            Usuario usuario = new Usuario();
            usuario.setNicUsu(nic_usu);
            usuario.setConUsu(con_usu);
            usuario.setIdTus(Integer.parseInt(tip_usu));
            
            
            ValidarUsuario validarUsuario = new ValidarUsuario(usuario);
            if(validarUsuario.esValido()){
               UsuarioBD usuarioBD = new UsuarioBD();
               int idUsu =  usuarioBD.insertarUsuario(con, usuario);
               System.out.println("La id insertada es: "+idUsu);
               usuario.setIdUsu(idUsu);
               TipoUsuario tipoUsuario = new TipoUsuario();
               TipoUsuarioBD tipoUsuarioBD = new TipoUsuarioBD();
               tipoUsuario = tipoUsuarioBD.getPorIdTus(con, usuario.getIdTus());
               String desTus = tipoUsuario.getDesTus();
               //Comprobar de que tipo de usuario se trata
               
               if(desTus.equals("Alumno")){
                    Alumno alumno = new Alumno();
                    alumno.setIdUsu(usuario.getIdUsu());
                    alumno.setAmaAlu(ama_usu);
                    alumno.setApaAlu(apa_usu);
                    alumno.setBolAlu(Integer.parseInt(bol_alu));
                    alumno.setCorAlu(cor_usu);
                    alumno.setNomAlu(nom_usu);
                    alumno.setGpoAlu(gpo_alu);
                    ValidarAlumno validarAlumno = new ValidarAlumno(alumno);
                    
                    if(validarAlumno.esValido()){
                        System.out.println("===El alumno es valido===");
                        AlumnoBD alumnoBD = new AlumnoBD();
                        alumnoBD.insertarAlumno(con, alumno);
                    }else{
                        System.out.println("===El alumno no es valido===");
                    }
               }else if(desTus.equals("Profesor")){
                    Bd.addProfesor(con, idUsu, apa_usu, ama_usu, nom_usu, cor_usu);
               }else if(desTus.equals("Tecnico")){
                    Bd.addTecnico(idUsu, apa_usu, ama_usu, nom_usu, cor_usu);
               }

            }
            con.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            /*
            try{
                if(Bd.usuarioNuevo(nic_usu)){
                    Bd.addUsuario(nic_usu, con_usu, Integer.parseInt(tip_usu));
                    int idUser = Bd.getIdUsuario(nic_usu, con_usu);
                    if(Bd.getDescTipoUsuario(Integer.parseInt(tip_usu)).equals("Alumno")){
                        Bd.addAlumno(idUser, apa_usu, ama_usu, nom_usu, gpo_alu, Integer.parseInt(bol_alu), cor_usu);
                    }else if(Bd.getDescTipoUsuario(Integer.parseInt(tip_usu)).equals("Profesor")){
                        Bd.addProfesor(idUser, apa_usu, ama_usu, nom_usu, cor_usu);
                    }else if(Bd.getDescTipoUsuario(Integer.parseInt(tip_usu)).equals("Tecnico")){
                        Bd.addTecnico(idUser, apa_usu, ama_usu, nom_usu, cor_usu);
                    }
                }else{
                    out.println("El nombre de usuario ya existe");   
                }
            }catch (ClassNotFoundException ex) {
            Logger.getLogger(AgregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }catch (SQLException ex) {
            Logger.getLogger(AgregarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
           response.sendRedirect("admin/agregar-usuario.jsp");

    }
}
