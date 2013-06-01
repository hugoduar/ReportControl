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
        <form class="form-inline" action="reporteLab-ana.jsp" method="GET">
        <label for="selLab">Seleccione el laboratorio:   </label><select id="selLab" name="selLab">
                                <%
                                
                                try {
                                    Connection con = Conexion.getConexion();
                                    ResultSet rs = Bd.getLaboratorios(con);
                                    String lab;
                                    int labid;
                                    while(rs.next()){
                                        lab = rs.getString("nom_lab");
                                        labid = rs.getInt("id_lab");
                                        out.println("<option value=\""+labid+"\">"+lab+"</option>");
                                    }
                                    con.close();
                                 } catch (Exception e) {
                              System.out.println("Error leyendo los laboratorios");
                            }
                                %>
                            </select>
        <button type="submit" class="btn">Mostrar</button>
        </form>
        <div class="input-prepend">
            <span class="add-on"><i class="icon-search"></i></span>
            <input class="span2" id="filterTBL" type="text" placeholder="Buscar">
	</div>
      

      <table class="table table-striped" id="f_tbl">
        <thead>
           
          <tr>
              
              <th><b>#</b></th>
              <th><b>Descripción del reporte</b></th>
              <th><b>Máquina</b></th>
              <th><b>Fecha del reporte</b></th>
              <th><b>Estado del reporte</b></th>
          </tr>
        </thead>
        <tbody>
          <%
          
          try{
              Connection con = Conexion.getConexion();
              ResultSet rs2 =  ReporteBD.getReportesPorIdLab(con, Integer.parseInt(request.getParameter("selLab")));
              session.setAttribute("idLab", Integer.parseInt(request.getParameter("selLab")));
                
               //rs = ReporteBD.getReportesPorIdLab(con, Integer.parseInt(request.getParameter("selLab")));     
            
            int idRep;
            String descRep;
            String nomEqui;
            String fecRep;
            String desEst;
            
            String row;
            while(rs2.next()){
                idRep = rs2.getInt("id_rep");
                descRep = rs2.getString("des_rep");
                nomEqui = rs2.getString("nom_equ");
                fecRep = rs2.getString("fec_rep");
                desEst = rs2.getString("des_ere");
                row = "<tr>\n"
                    + "\t<td>"+idRep+"</td>\n"
                    + "\t<td>"+descRep+"</td>\n"
                    + "\t<td>"+nomEqui+"</td>\n"
                    + "\t<td>"+fecRep+"</td>\n"
                    + "\t<td>"+desEst+"</td>\n"
                    + "<tr>";
                out.println(row);
                
            }
            con.close();
          }catch (Exception e) {
            System.out.println("Error en los reportes por laboratorio");
          }
          
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
