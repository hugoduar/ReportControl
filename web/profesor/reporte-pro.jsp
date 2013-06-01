<%@page import="conexion.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, bd.Bd"%>
<!DOCTYPE html>
<html lang="es">
  <head>
      <%
        if(session==null || !session.getAttribute("rol").equals("profesor")){
          response.sendRedirect("../404.html");
        } 
        ResultSet rs = null;
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
            padding-top: 50px;
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
              <li><a href="#">Inicio</a></li>
              <li class="active"><a href="reporte-pro.jsp">Reporte</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="historial-pro.jsp">Mis reportes</a></li>
                  <li><a href="datos-pro.jsp">Mis Datos</a></li>
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
            <form id="formReporte" class="form-horizontal" action="../AgregarReporteProfesor" method="POST">
                <fieldset>
                    <legend>Enviar un reporte</legend>
                    <div class="control-group">
                        <label class="control-label" for="txtDescReporte">Descripción del reporte</label>
                        <div class="controls">
                            <textarea id="txtDescReporte" name="txtDescReporte" rows="4"></textarea>
                            <p class="help-block">Se permiten únicamente 200 caracteres.</p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="selLaboratorio">Laboratorio</label>
                        <div class="controls">
                            <select id="selLaboratorio" name="selLaboratorio" required>
                              <%
                              
                                try {
                                  Connection con = Conexion.getConexion();
                                  String id_lab;
                                  String nom_lab;
                                  rs = Bd.getLaboratorios(con);
                                  while (rs.next()) {
                                    id_lab = rs.getString("id_lab");
                                    nom_lab = rs.getString("nom_lab");
                                    String infoUsu = "<option value=\""+id_lab+"\">"+nom_lab+"</option>";
                                      out.println(infoUsu);
                                  }
                                  con.close();
                                } catch (Exception e)  {
                                  System.out.println("Error en 'rep-pro'");
                                }
                              
                            %>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputNumero">Numero de la máquina</label>
                        <div class="controls">
                            <input type="text" class="input-mini" id="inputNumero" name="inputNumero" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputNumero">Tipo de falla</label>
                        <div class="controls">
                            <label class="radio">
                              <input type="radio" name="optionFalla" id="software" value="software" checked>
                              Software
                            </label>
                            <label class="radio">
                              <input type="radio" name="optionFalla" id="hardware" value="hardware">
                              Hardware 
                            </label>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputDesFalla" required>Componente ó software que falla</label>
                        <div class="controls">
                            <input type="text" class="input-medium" id="inputDesFalla" name="inputDesFalla" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputAsignatura" required>Asignatura</label>
                        <div class="controls">
                            <input type="text" class="input-large" id="inputAsignatura" name="inputAsignatura" required>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <button class="btn btn-primary" id="btnAut" type="submit">Enviar</button>
                        <button class="btn">Cancelar</button>
                    </div>
                </fieldset>
            </div>
            </form>
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
