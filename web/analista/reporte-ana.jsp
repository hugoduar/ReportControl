<%@page import="datos.JoinBD"%>
<%@page import="modelo.EstadoReporte"%>
<%@page import="modelo.Equipo"%>
<%@page import="modelo.Laboratorio"%>
<%@page import="modelo.Laboratorio"%>
<%@page import="conexion.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="datos.ReporteBD"%>
<%@page import="modelo.Reporte"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, bd.Bd, java.sql.ResultSet"%>
<%
if(session==null || !session.getAttribute("rol").equals("analista")){
  response.sendRedirect("../404.html");
}
String nusuario = (String) session.getAttribute("nickUser");
String fechaIni = request.getParameter("fecIni");
String fechaFin = request.getParameter("fecFin");
%>
<!DOCTYPE html>
<html lang="es">
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
    <script src="../js/table_filter.js"></script>
    <script>
        $(function(){ 
	        $("#filterTBL").table_filter(
	        	{'table':'#f_tbl',
	        	'filter_inverse':false,
                'enable_space':false});
	    });
    </script>
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
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Reportes<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="reporte-ana.jsp">Reportes por fecha</a></li>
                  <li><a href="reporteLab-ana.jsp">Reportes por laboratorios</a></li>
                  <li><a href="reporteEq-ana.jsp">Reportes por equipos</a></li>
                  
                </ul>
              </li>
              <li class="dropdown ">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cuenta<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="datos-ana.jsp">Mis Datos</a></li>
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
        <form class="form-inline" action="reporte-ana.jsp" method="POST">
        <label for="fecIni">Fecha  </label><input id="fecIni" name="fecIni" type="date"/>
        <label for="fecIni">A: </label><input id="fecFin" name="fecFin" type="date"/>
        <button type="submit" class="btn">Mostrar</button>
        </form>
        <div class="input-prepend">
            <span class="add-on"><i class="icon-search"></i></span>
            <input class="span2" id="filterTBL" type="text" placeholder="Buscar">
	</div>
      <form action="../GenerarPdf" method="POST"><button id="btnExport" class="btn btn-info pull-right">PDF</button></form>

      <table class="table table-striped" id="f_tbl">
        <thead>
           
          <tr>
              
              <th><b>#</b></th>
              <th><b>Descripción del reporte</b></th>
              <th><b>Laboratorio</b></th>
              <th><b>Máquina</b></th>
              <th><b>Fecha del reporte</b></th>
              <th><b>Estado del reporte</b></th>
          </tr>
        </thead>
        <tbody>
          <%
          Connection con  = Conexion.getConexion();
          
          try{
              ResultSet rs;
            if(fechaFin!=null && fechaIni!=null){
                rs = JoinBD.getReportesPorFecha(con, fechaIni, fechaFin);
                session.setAttribute("fecIni", fechaIni);
                session.setAttribute("fecFin", fechaFin);
            }else{
                rs = JoinBD.getReportesPorFecha(con, "0000-00-00", "9999-12-31");
                session.setAttribute("fecIni", "0000-00-00");
                session.setAttribute("fecFin", "9999-12-31");
            }
            int idRep;
            String descRep;
            String labEqui;
            String nomEqui;
            String fecRep;
            String desEst;
            int idProf;
            String prof;
            int idAlu;
            String alu;
            
            String row;
            while(rs.next()){
                idRep = rs.getInt("id_rep");
                descRep = rs.getString("des_rep");
                labEqui = rs.getString("nom_lab");
                nomEqui = rs.getString("nom_equ");
                fecRep = rs.getString("fec_rep");
                desEst = rs.getString("des_ere");
                idProf = rs.getInt("id_pro");
                idAlu = rs.getInt("id_alu");
                
                prof = Bd.getNickProfesor(idProf);
                alu = Bd.getNickAlumno(idAlu);
                
                row = "<tr>\n"
                    + "\t<td>"+idRep+"</td>\n"
                    + "\t<td>"+descRep+"</td>\n"
                    + "\t<td>"+labEqui+"</td>\n"
                    + "\t<td>"+nomEqui+"</td>\n"
                    + "\t<td>"+fecRep+"</td>\n"
                    + "\t<td>"+desEst+"</td>\n"
                    + "<tr>";
                out.println(row);
            }
          }catch (Exception e) {
            System.out.println("No lee de la tabla");
          }
          con.close();
          %>
        </tbody>
      </table>

      <hr>
      <footer>
        <p>&copy; EnterAll 2013</p>
      </footer>

    </div> <!-- /container -->
    <script src="../js/bootstrap.js"></script>
    <script>
      $('#btnSalir').click(function(){
        $('#formLogout').submit();
      });
    </script>

  </body>
</html>
