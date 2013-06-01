<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.ResultSet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, bd.Bd"%>
<!DOCTYPE html>
<html lang="es">
  <head>
      <%
        if(session==null || !session.getAttribute("rol").equals("tecnico")){
            response.sendRedirect("../404.html");
        }
        
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
              <li class="active"><a href="reporte-tec.jsp">Reportes</a></li>
              <li class="dropdown">
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
        <form id="formRep" action="../RepararEquipo" method="POST">
        <table class="table table-striped">
        <thead>
          <tr>
              <th><b>#</b></th>
              <th><b>Descripción del reporte</b></th>
              <th><b>Laboratorio</b></th>
              <th><b>Máquina</b></th>
              <th><b>Fecha del reporte</b></th>
              <th><b>Reparar</b></th>
          </tr>
        </thead>
        <tbody>
            <%
          try{
            ResultSet rs = Bd.getReportes();
            int idRep;
            String descRep;
            String labEqui;
            String nomEqui;
            String fecRep;
            int idEst;
            String row;
            
            while(rs.next()){
                idEst = rs.getInt("id_ere");
                idRep = rs.getInt("id_rep");
                descRep = rs.getString("des_rep");
                labEqui = rs.getString("nom_lab");
                nomEqui = rs.getString("nom_equ");
                fecRep = rs.getString("fec_rep");
                
                
                row = "<tr>\n"
                    + "\t<td>"+idRep+"</td>\n"
                    + "\t<td>"+descRep+"</td>\n"
                    + "\t<td>"+labEqui+"</td>\n"
                    + "\t<td>"+nomEqui+"</td>\n"
                    + "\t<td>"+fecRep+"</td>\n"
                    + "\t<td><a href=\"#espReparacion\" data-toggle=\"modal\"><button class=\"btn\" id=\""+idRep+"\" name=\"boton\" value=\""+idRep+"\"><i class=\"icon-wrench\"></i></button></a></td>\n"
                    + "<tr>";
                if(idEst==1){
                    out.println(row);
                }else{}
                
            }
          }catch (Exception e) {
            System.out.println("No lee de la tabla");
          }                 
          %>
        </tbody>
    </form>
      </table>
      <div id="espReparacion" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="lblEspRep" aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="lblEspRep">Reparación</h3>
          </div>
          <div class="modal-body">
            
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="inputNumero">Numero del reporte</label>
                        <div class="controls">
                            <input type="text" class="input-mini" id="inputNumero" name="inputNumero" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="txtDescRep">Breve descripción de la reparación, cambios, instalaciones, etc.</label>
                        <div class="controls">
                            <textarea class="input-xlarge" name="txtDescRep" id="txtDescRep" rows="6"></textarea>
                        </div>
                    </div>
          </div>
          <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</button>
            <button class="btn btn-primary" id="btnReparar">Guardar</button>
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
        $('#btnReparar').click(function(){ 
            $('#formRep').submit();
        })
      $('#btnSalir').click(function(){
        $('#formLogout').submit();
      });
    </script>

  </body>
</html>
