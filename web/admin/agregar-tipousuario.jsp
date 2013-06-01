<%-- 
    Document   : auth
    Created on : 02-may-2013, 1:44:07
    Author     : Hugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, bd.Bd"%>
<% if(session.getAttribute("rol")==null){
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
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
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
              <li class="dropdown ">
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
      <div class="row-fluid">
        <div class="span7">
            <form class="form-horizontal" action="../AgregarTipoUsuario" method="POST">
                <fieldset>
                    <legend>Agregar un tipo usuario</legend>
                    <div class="control-group">
                        <label class="control-label" for="inputNomenclaturaUsuario">Nomenclatura del tipo de usuario</label>
                        <div class="controls">
                            <input type="text" class="input-xlarge" id="inputNomenclaturaUsuario" name="inputNomenclaturaUsuario">
                            
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputTiempoSesion">Tiempo por sesión</label>
                        <div class="controls">
                            <input type="text" class="input-mini" id="inputTiempoSesion" name="inputTiempoSesion">
                            <p class="help-block">El tiempo se introduce en minutos.</p>
                        </div>
                    </div>
                        <button type="submit" class="btn btn-primary">Agregar</button>
                        <button class="btn">Cancelar</button>
                    </div>
                </fieldset>
            </form>
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
