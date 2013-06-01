<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("rol")!=null){
    if(session.getAttribute("rol").equals("Administrador")){
        response.sendRedirect("admin/inicio.jsp");
    }else{
        response.sendRedirect(session.getAttribute("rol")+"/inicio.jsp");
    }
}%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Entrar a Report Control</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

      .titulo{
        max-width: 450px;
        padding: 19px 29px 29px;
        margin: 0 auto 0;
        
      }
      .titulo h1{
        display: inline-block;
      }
      .titulo img{
        display: inline-block;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">
      <div class="titulo">
        <img src="img/logo-ipn.png" />
        <h1>Report Control</h1>
        <img src="img/logo-cecyt9.png" />
      </div>
      <form class="form-signin" id="formLogin" action="Login" method="POST">
        <h2 class="form-signin-heading">Entrar</h2>
        <input id="inpUsuario" name="inpUsuario" type="text" class="input-block-level" placeholder="Usuario" required>
        <input id="inpPassword" name="inpPassword" type="password" class="input-block-level" placeholder="Contraseña" required>
        
        <!--<label class="checkbox">
          <input type="checkbox" value="remember-me" > Recuerdame
        </label>-->
        
        <button class="btn btn-large btn-primary" type="submit" id="btnEntrar">Entrar</button>
        <%
        if(request.getAttribute("rechazado")!=null){
            out.println("<div id=\"msjError\" class=\"alert alert-error\">"
                        +"Datos invalidos"
                        +"</div>");
        }
        %>
        
      </form>
      <!-- recuperarModal -->
      <div id="recuperarModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="lblModal" aria-hidden="true">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h3 id="lblModal">Recuperar mi contraseña</h3>
        </div>
        <div class="modal-body">
          <form class="form-horizontal">
            <div class="control-group">
              <label class="control-label" for="inpCorreoRecuperar">Correo Electronico</label>
              <div class="controls">
                <input type="text" id="inpCorreoRecuperar" placeholder="ejemplo@dominio.com">
              </div>
            </div>
            <div id="msjRecuperar" class="alert alert-info hidden">
              <!--Mensaje de recuperacion de contraseña-->
              No implementado
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button>
          <button class="btn btn-primary" id="btnRecuperar">Enviar</button>
        </div>
      </div>
    </div> <!-- /container -->
    
    <script src="js/jquery.js"></script>
    <script>
      $('#btnRecuperar').click(function(){
        $('#msjRecuperar').removeClass("hidden");
      });
    </script>
    <script src="js/bootstrap.js"></script>

  </body>
</html>

=======
<%-- 
    Document   : index
    Created on : 30-may-2013, 2:37:24
    Author     : Hugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
>>>>>>> 6efab16c8837e4e3634b7b257dd6b17b2d60c6d1
