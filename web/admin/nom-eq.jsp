<%-- 
    Document   : auth
    Created on : 02-may-2013, 1:44:07
    Author     : Hugo
--%>

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
              <li class="active"><a href="#1" data-toggle="tab">Agregar Equipos</a></li>
              <li><a href="#2" data-toggle="tab">Eliminar Equipos</a></li>
          </ul>
          <div class="tab-content">
              <div class="tab-pane active" id="1">
                  <form class="form-horizontal" action="../AgregarEquipo" method="POST">
                      <fieldset>
                          <div class="control-group">
                              <label class="control-label" for="inputNomEq">Nombre del equipo</label>
                              <div class="controls">
                                  <input type="text" class="input-xlarge" id="inputNomEq" name="inputNomEq">
                              </div>
                          </div>
                          <div class="control-group">
                              <label class="control-label" for="selLabEq">Selecciona el laboratorio</label>
                              <div class="controls">
                                  <select id="selLabEq" name="selLabEq">
                                      <%
                                        try {
                                            ResultSet rs = Bd.getLaboratorios();
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
                          <div class="form-actions">
                              <button type="submit" class="btn btn-primary">Agregar</button>
                          </div>
                      </fieldset>
                  </form>
              </div>
              <div class="tab-pane" id="2">
                  <form action="../EliminarEquipo" method="POST">
                      <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Laboratorio</th>
                                <th>Nombre</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                                <%
                                try {
                                    ResultSet rs = Bd.getEquipos();
                                    String eliminar = "";
                                    String nomEqu;
                                    String nomLab;
                                    while(rs.next()){
                                        nomLab = Bd.getNombreLaboratorio(rs.getInt("id_lab"));
                                        nomEqu = rs.getString("nom_equ");
                                        eliminar = "<tr><td>"+nomLab+"</td><td>"+nomEqu+"</td><td><button class=\"btn\" id=\""+nomEqu+"\" name=\"boton\" value=\""+nomEqu+"\"><i class=\"icon-trash\"></i></button></td></tr>";
                                        out.println(eliminar);
                                    }
                                    } catch (Exception e) {
                                        System.out.println("No lee de la tabla");
                                    }
                                %>
                        </tbody>
                          
                      </table>
                  </form>
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
