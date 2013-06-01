<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.ResultSet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, bd.Bd"%>
<!DOCTYPE html>
<html lang="es">
  <head>
      <%
        if(session==null || !session.getAttribute("rol").equals("tecnico")){
            response.sendRedirect("../404.html");
        }
        String nusuario = (String) session.getAttribute("nickUser");
      %>
    <meta charset="utf-8">
    <title>Report Control - EnterAll</title>
    <meta name="description" content="Report Control">
    <meta name="author" content="hugoduar"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
    <script src="../js/jquery.js"></script>
    <style>
        #contenedor{
            padding-top: 100px;
        }
        
    </style>
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand"><%out.print(session.getAttribute("rol"));%></a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href="inicio.jsp">Inicio</a></li>
              <li><a href="reporte-tec.jsp">Reportes</a></li>
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="datos-tec.jsp">Mis Datos</a></li>
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
          <li class="active"><a href="#1" data-toggle="tab">Mis datos</a></li>
          <li><a href="#2" data-toggle="tab">Modificar contraseña</a></li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane active" id="1">
              <p><b>Nombre: </b><i><%out.print(Bd.getNombreTecnico(nusuario));%></i></p>
              <p><b>Apellido Paterno: </b><i><%out.print(Bd.getApellidoPaternoTecnico(nusuario));%></i></p>
              <p><b>Apellido Materno: </b><i><%out.print(Bd.getApellidoMaternoTecnico(nusuario));%></i></p>
          </div>
          <div class="tab-pane" id="2">
            <form class="form-horizontal" action="../CambiarPassword" method="POST">
              <div class="control-group">
                <label class="control-label" for="inputPass">Contraseña Actual</label>
                <div class="controls">
                  <input type="password" id="inputPass" name="inputPass" placeholder="****">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="inputPassNew">Nueva Contraseña</label>
                <div class="controls">
                  <input type="password" id="inputPassNew" name="inputPassNew" placeholder="****">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="inputPassNewRep">Repita Contraseña</label>
                <div class="controls">
                  <input type="password" id="inputPassNewRep" name="inputPassNewRep" placeholder="****">
                </div>
              </div>
              
            </form>
            <div class="control-group">
              <div class="controls">
                <button id="btnCambiar" class="btn">Cambiar</button>
              </div>
            </div>
            <div id="msjError" class="alert alert-error hidden">
              <button type="button" class="close" data-dismiss="alert">×</button>
              <p></p>
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
    <script src="../js/cambiarpsw.js"></script>
    <script>
        $('#btnReparar').click(function(){ 
            $('#formRep').submit();
        });
      $('#btnSalir').click(function(){
        $('#formLogout').submit();
      });
    </script>

  </body>
</html>
