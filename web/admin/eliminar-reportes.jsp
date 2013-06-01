<%-- 
    Document   : auth
    Created on : 02-may-2013, 1:44:07
    Author     : Hugo
--%>
<%@page import="conexion.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet, bd.Bd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <script src="../js/table_filter.js"></script>
    <script>
        $(function(){ 
	        $("#filterTBL").table_filter(
	        	{'table':'#f_tbl',
	        	'filter_inverse':false,
                'enable_space':false});
	    });
    </script>
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
              <li class="dropdown active">
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
        <div class="input-prepend">
            <span class="add-on"><i class="icon-search"></i></span>
            <input class="span2" id="filterTBL" type="text" placeholder="Buscar">
	</div>
     <form action="../EliminarReporte" method="POST">
      <table class="table table-striped" id="f_tbl">
        <thead>
          <tr>
              <th><b>#</b></th>
              <th><b>Descripción del reporte</b></th>
              <th><b>Laboratorio</b></th>
              <th><b>Máquina</b></th>
              <th><b>Fecha del reporte</b></th>
              <th><b>Estado del reporte</b></th>
              <th><b>Profesor</b></th>
              <th><b>Alumno</b></th>
              <th><b>Eliminar</b></th>
          </tr>
        </thead>
        <tbody>
            
          <%
          Connection con = Conexion.getConexion();
          try{
            ResultSet rs = Bd.getReportesAdm(con);
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
                    + "\t<td>"+prof+"</td>\n"
                    + "\t<td>"+alu+"</td>\n"
                    + "\t<td><button class=\"btn\" id=\""+idRep+"\" name=\"boton\" value=\""+idRep+"\"><i class=\"icon-trash\"></i></button></td>\n"
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
    </form>
        <div class="modal hide fade" id="modalAviso">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              <h3>Aviso</h3>
            </div>
            <div class="modal-body">
              <p>¿Estas seguro que deseas eliminar?</p>
              <p><% out.println(request.getParameter("boton")); %></p>
            </div>
            <div class="modal-footer">
              <a href="#" class="btn" data-dismiss="modal">Cancelar</a>
              <form action="eliminar-reportes.jsp" method="POST">
                  <input type="hidden" value="<% out.println(request.getParameter("boton")); %>" name="inpIdRep"/>
                  <button class="btn btn-primary">Continuar</button>
              </form>
            </div>
          </div>
      <hr>

      <footer>
        <p>&copy; EnterAll 2013</p>
      </footer>

    </div> <!-- /container -->
    <script src="../js/bootstrap.js"></script>
    <script>
        $('#boton').click(function(){
            $('#modalAviso').modal('toggle');
        });
      $('#btnSalir').click(function(){
        $('#formLogout').submit();
      });
    </script>

  </body>
</html>
