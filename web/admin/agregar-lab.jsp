<%@page import="conexion.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.ResultSet, bd.Bd"%>
<% if(session.getAttribute("rol")==null){
    response.sendRedirect("../404.html");
}else if(!session.getAttribute("rol").equals("Administrador")){
  response.sendRedirect("../404.html");
}
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
            padding-top: 100px;
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
              <li><a href="auth.jsp">Inicio</a></li>
              <li class="dropdown">
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
              <li class="dropdown active">
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
              <li class="active"><a href="#1" data-toggle="tab">Cambiar nombres</a></li>
              <li><a href="#2" data-toggle="tab">Agregar laboratorio</a></li>
              <li><a href="#3" data-toggle="tab">Borrar laboratorio</a></li>
          </ul>
          <div class="tab-content">
              <div class="tab-pane active" id="1">
                <!--CAMBIAR LAB-->
                  <form action="../ModificarLaboratorio" class="form-horizontal" method="POST">
                    <div class="control-group">
                        <label class="control-label" for="selLab">Selecciona el Laboratorio</label>
                        <div class="controls">
                            <select id="selLab" name="selLab">
                                <%
                                Connection con = Conexion.getConexion();
                                try {
                                    ResultSet rs = Bd.getLaboratorios(con);
                                    String lab;
                                    int labid;
                                    while(rs.next()){
                                        lab = rs.getString("nom_lab");
                                        labid = rs.getInt("id_lab");
                                        out.println("<option value=\""+labid+"\">"+lab+"</option>");
                                    }
                                 } catch (Exception e) {
                              System.out.println("No lee de la tabla");
                            }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputNombreLab">Nuevo Nombre</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="inputNombreLab" name="inputNombreLab" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <button type="submit" class="btn btn-primary">Modificar</button>
                    </div>
                  </form>
              </div>
              <!--AGREGAR LAB-->
              <div class="tab-pane" id="2">
                  <form action="../AgregarLaboratorio" class="form-horizontal" method="POST">
                    <form class="form-horizontal">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label" for="inputNLab">Nombre laboratorio</label>
                                <div class="controls">
                                    <input type="text" class="input-xlarge" id="inputNLab" name="inputNLab">
                                </div>
                            </div>
                            <div class="control-group">
                                <button type="submit" class="btn btn-primary">Agregar</button>
                            </div>
                        </fieldset>
                    </form>
                      
                  </form>
              </div>
              <!--BORRAR LAB-->
              <div class="tab-pane" id="3">
                  <form action="../EliminarLaboratorio" method="POST">
                      <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                                <%
                                try {
                                    ResultSet rs = Bd.getLaboratorios(con);
                                    String eliminar = "";
                                    String nomLab;
                                    int labid;
                                    while(rs.next()){
                                        nomLab = rs.getString("nom_lab");
                                        labid = rs.getInt("id_lab");
                                        eliminar = "<tr><td>"+labid+"</td><td>"+nomLab+"</td><td><button class=\"btn\" id=\""+labid+"\" name=\"boton\" value=\""+labid+"\"><i class=\"icon-trash\"></i></button></td></tr>";
                                        out.println(eliminar);
                                    }
                                    } catch (Exception e) {
                                        System.out.println("No lee de la tabla");
                                    }
                                con.close();
                                %>
                        </tbody>
                          
                      </table>
                  </form>
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
