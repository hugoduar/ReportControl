<%@page import="datos.TipoUsuarioBD"%>
<%@page import="modelo.TipoUsuario"%>
<%@page import="modelo.Usuario"%>
<%@page import="datos.UsuarioBD"%>
<%@page import="java.util.Iterator"%>
<%@page import="datos.AlumnoBD"%>
<%@page import="modelo.Alumno"%>
<%@page import="conexion.Conexion"%>
<%@page import="java.util.List"%>
<%@page import="javax.ws.rs.PUT"%>
<%@page import="org.omg.CORBA.PUBLIC_MEMBER"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, bd.Bd" %>
<% 
if(session.getAttribute("rol")==null){
    response.sendRedirect("../404.html");
}else if(!session.getAttribute("rol").equals("Administrador")){
  response.sendRedirect("../404.html");
}
ResultSet rs = null;
%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <title>Report Control - EnterAll</title>
    <meta name="description" content="Report Control">
    <meta name="author" content="hugoduar"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="../js/jquery.js"></script>
    <style type="text/css">
        #contenedor{
            padding-top: 80px;
        }
    </style>
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand"><%out.println(session.getAttribute("rol")
          );%></a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li ><a href="auth.jsp">Inicio</a></li>
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuarios<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="agregar-usuario.jsp">Agregar Usuario</a></li>

                  <li><a href="eliminar-usuario.jsp">Eliminar Usuario</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reportes<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="consultar-reportes.jsp">Consultar Reportes</a></li>
                  <li><a href="eliminar-reportes.jsp">Eliminar Reportes</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Sistema<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="agregar-lab.jsp">Configurar laboratorios</a></li>
                  <li><a href="nom-eq.jsp">Nomenclatura de los Equipos</a></li>
                  <li><a href="agregar-tipousuario.jsp">Agregar un tipo de usuario</a></li>
                  
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="datos-administrador.jsp">Mis Datos</a></li>
                </ul>
              </li> 
            </ul>
              <form id="formLogout" class="form-search pull-right" action="/ReCo/Logout">
                <button id="btnSalir" class="btn pull-right">Salir</button>
              </form>
              
              
          </div><!--/.nav-collapse -->

        </div>
      </div>
    </div>

      <div class="container" id="contenedor">
        <div class="tabbable">
          <ul class="nav nav-tabs">
            <li class="active"><a href="#tabAlu" data-toggle="tab">Alumnos</a></li>
            <li><a href="#tabPro" data-toggle="tab">Profesores</a></li>
            <li><a href="#tabTec" data-toggle="tab">Tecnicos</a></li>
            <li><a href="#tabUsu" data-toggle="tab">Otros Usuarios</a></li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active" id="tabAlu">
              <table class="table table-hover">
                <thead>
                    <tr>
                        <th><b>Nick de Usuario</b></th>
                        <th><b>Apellido Paterno</b></th>
                        <th><b>Apellido Materno</b></th>
                        <th><b>Nombre(s)</b></th>
                        <th><b>Boleta</b></th>
                        <th><b>Grupo</b></th>
                        <th><b>Eliminar</b></th>
                    </tr>
                </thead>
                <tbody>
                    <form action="../EliminarUsuario" method="POST">
                    <input type="hidden" name="inpTip" value="1">
                <%
                Connection con  = Conexion.getConexion();
                con.setAutoCommit(false);
                String infoFinalUsu;
                Alumno alumno = new Alumno();
                AlumnoBD alumnoBD = new AlumnoBD();
                UsuarioBD usuBD = new UsuarioBD();
                Usuario usu = new Usuario();
                List<Alumno> listaAlumno = alumnoBD.getAlumnos(con);
                Iterator itrAlumno = listaAlumno.iterator();
                while(itrAlumno.hasNext()){
                    alumno = (Alumno) itrAlumno.next();
                    usu  = usuBD.getPorIdUsu(con, alumno.getIdUsu());
                    infoFinalUsu = "<tr><td>"+usu.getNicUsu()+"</td><td>"+alumno.getApaAlu()+"</td><td>"+alumno.getAmaAlu()+"</td><td>"+alumno.getNomAlu()+"</td><td>"+alumno.getBolAlu()+"</td><td>"+alumno.getGpoAlu()+"</td><td><input type=\"hidden\" name=\"inpIdUsu\" value=\""+usu.getNicUsu()+"\"><button class=\"btn\" id=\""+usu.getNicUsu()+"\" name=\"boton\" value=\""+usu.getNicUsu()+"\"><i class=\"icon-trash\"></i></button></td></tr>";
                    out.println(infoFinalUsu);
                }
                            %>
                    </form>
                </tbody>
              </table>
            </div>
            <div class="tab-pane" id="tabPro">
                <table class="table table-hover">
                <thead>
                    <tr>
                        <th><b>Nick de Usuario</b></th>
                        <th><b>Apellido Paterno</b></th>
                        <th><b>Apellido Materno</b></th>
                        <th><b>Nombre(s)</b></th>
                        <th><b>Eliminar</b></th>
                    </tr>
                </thead>
                <tbody>
                    <form action="../EliminarUsuario" method="POST">
                    <input type="hidden" name="inpTip" value="2">
                <%
                              try {
                              int id_usu;
                              int id_alu;
                              String nic_usu;
                              String apa_usu;
                              String ama_usu;
                              String nom_usu;
                              rs = Bd.getDatosProfesores(con);
                              while (rs.next()) {
                                id_alu = rs.getInt("id_pro");
                                nic_usu = rs.getString("nic_usu");
                                apa_usu = rs.getString("apa_pro");
                                ama_usu = rs.getString("ama_pro");
                                nom_usu = rs.getString("nom_pro");

                                String infoUsu = "<tr><td>"+nic_usu+"</td><td>"+apa_usu+"</td><td>"+ama_usu+"</td><td>"+nom_usu+"</td><td><input type=\"hidden\" name=\"inpIdUsu\" value=\""+nic_usu+"\"><button class=\"btn\" id=\""+nic_usu+"\" name=\"boton\" value=\""+nic_usu+"\"><i class=\"icon-trash\"></i></button></td></tr>";
                                  out.println(infoUsu);
                              }
                            } catch (Exception e) {
                              System.out.println("No lee de la tabla");
                            }
                            %>
                    </form>
                </tbody>
              </table>
            </div>
            <div class="tab-pane" id="tabTec">
              <table class="table table-hover">
                <thead>
                    <tr>
                        <th><b>Nick de Usuario</b></th>
                        <th><b>Apellido Paterno</b></th>
                        <th><b>Apellido Materno</b></th>
                        <th><b>Nombre(s)</b></th>
                        <th><b>Eliminar</b></th>
                    </tr>
                </thead>
                <tbody>
                <form action="../EliminarUsuario" method="POST">
                    <input type="hidden" name="inpTip" value="3">
                <%
                              try {
                              int id_usu;
                              int id_alu;
                              String nic_usu;
                              String apa_usu;
                              String ama_usu;
                              String nom_usu;
                              
                              rs = Bd.getDatosTecnicos(con);
                              while (rs.next()) {
                                id_alu = rs.getInt("id_tec");
                                nic_usu = rs.getString("nic_usu");
                                apa_usu = rs.getString("apa_tec");
                                ama_usu = rs.getString("ama_tec");
                                nom_usu = rs.getString("nom_tec");

                                String infoUsu = "<tr><td>"+nic_usu+"</td><td>"+apa_usu+"</td><td>"+ama_usu+"</td><td>"+nom_usu+"</td><td><input type=\"hidden\" name=\"inpIdUsu\" value=\""+nic_usu+"\"><button class=\"btn\" id=\""+nic_usu+"\" name=\"boton\" value=\""+nic_usu+"\"><i class=\"icon-trash\"></i></button></td></tr>";
                                  out.println(infoUsu);
                              }
                            } catch (Exception e) {
                              System.out.println("No lee de la tabla");
                            }
                            %>
                    </form>
                </tbody>
              </table>
            </div>
          
          <div class="tab-pane" id="tabUsu">
              <table class="table table-hover">
                <thead>
                    <tr>
                        <th><b>Nick de Usuario</b></th>
                        <th><b>Rol de usuario</b></th>
                        <th><b>Eliminar</b></th>
                    </tr>
                </thead>
                <tbody>
                <form action="../EliminarUsuario" method="POST">
                    <input type="hidden" name="inpTip" value="3">
                <%
                TipoUsuario tipoUsuario;
                TipoUsuarioBD tipoUsuarioBD = new TipoUsuarioBD();
                List<Usuario> listaUsuario = usuBD.getUsuarios(con);
                Iterator itrUsuario = listaUsuario.iterator();
                while(itrUsuario.hasNext()){
                    usu = (Usuario) itrUsuario.next();
                    tipoUsuario = tipoUsuarioBD.getPorIdTus(con, usu.getIdTus());

                    String infoUsu = "<tr><td>"+usu.getNicUsu()+"</td>"
                                + "<td>"+tipoUsuario.getDesTus()+"</td>"
                                + "<td><input type=\"hidden\" name=\"inpIdUsu\" value=\""+usu.getNicUsu()+"\">"
                                + "<button class=\"btn\" id=\""+usu.getNicUsu()+"\" name=\"boton\" value=\""+usu.getNicUsu()+"\">"
                                + "<i class=\"icon-trash\"></i></button></td></tr>";
                    out.println(infoUsu);
                }
                con.commit();
                con.close();             
                            %>
                    </form>
                </tbody>
              </table>
            </div>
          </div>
                    </div>
        </div>        
      
        
      </div>

      <hr>
      <footer>
        <p>&copy; EnterAll 2013</p>
      </footer>

    </div> <!-- /container -->
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script>
        
      $('#btnSalir').click(function(){
        $('#formLogout').submit();
      });
    </script>

  </body>
</html>