<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, bd.Bd"%>
<% 
if(session==null || !session.getAttribute("rol").equals("alumno")){
  response.sendRedirect("../404.html");
}
String nic = (String) session.getAttribute("nickUser");
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
              <li><a href="reporte-alu.jsp">Reporte</a></li>
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="historial-alu.jsp">Mis reportes</a></li>
                  <li><a href="datos-alu.jsp">Mis Datos</a></li>
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
      <table class="table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Descripción</th>
                  <th>Equipo</th>
                  <th>Laboratorio</th>
                  <th>Fecha</th>
                  <th>Estado</th>
                  <th>Profesor que autorizó</th>
                </tr>
              </thead>
              <tbody>
                  <%
                  ResultSet rs = Bd.getReportesAlumno(nic);
                  int idRep;
                  String desc;
                  String nomEq;
                  String nomLab;
                  String fecRep;
                  String estRep;
                  int idPro;
                  String nomPro;
                  
                  String row;
                  while(rs.next()){
                    idRep = rs.getInt("id_rep");
                    desc = rs.getString("des_rep");
                    nomEq = rs.getString("nom_equ");
                    nomLab = rs.getString("nom_lab");
                    fecRep = rs.getString("fec_rep");
                    estRep = rs.getString("des_ere");
                    
                    idPro = rs.getInt("id_pro");
                    
                    nomPro = Bd.getNombreCompletoProfesor(idPro);
                    row = "<tr>"
                    + "<td>"+idRep+"</td>"
                    + "<td>"+desc+"</td>"
                    + "<td>"+nomEq+"</td>"
                    + "<td>"+nomLab+"</td>"        
                    + "<td>"+fecRep+"</td>"
                    + "<td>"+estRep+"</td>"
                    + "<td>"+nomPro+"</td>"                                         
                    + "</tr>"; 
                    out.println(row);
                  }
                  %>
              </tbody>
            </table>
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