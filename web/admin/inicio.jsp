<%@page contentType="text/html" pageEncoding="UTF-8" import="token.Clave;"%>
<!DOCTYPE html>
<% 
if(session.getAttribute("rol")==null){
    response.sendRedirect("../404.html");
}else if(!session.getAttribute("rol").equals("Administrador")){
  response.sendRedirect("../404.html");
}
%>
<html>
    <head>
    <meta charset="utf-8">
    <title>Entrar a Report Control</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Le styles -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
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
        <h1>Administración</h1>
      </div>
         <%
              Clave clv = new Clave();
              String comb = clv.GenerarCombinacion();
     %>
      <form class="form-signin" id="formLoginToken">
        <h2 class="form-signin-heading">Numero a convertir</h2>
        <div id="msjBeforeTkn" class="alert alert-info "><%out.println(comb);%></div>
        <input id="inpAftTkn"  pattern="[0-9]" name="inpAftTkn" type="text" class="input-block-level" placeholder="Numero Generado">
        <a class="btn btn-large btn-primary" id="btnEntrar">Entrar</a>
        <div id="msjError" class="alert hidden ">

        </div>
      </form>
        
    </div> <!-- /container -->
    
    <script src="../js/jquery.js"></script>
    <script>
      $('#btnEntrar').click(function(){
          $.post('../Token', {inpAftTkn:$('#inpAftTkn').val(),inpCombinacion:<%out.println(comb);%>}, 
          function(data){
              var dataJson = $.parseJSON(data);
              var url = "";
              var msj = "";
              var entrada = false;
              $.each(dataJson, function(index, element) {
                  msj = element.msj;
                  url = element.url;
                  entrada = element.entrada;
              });
              if(entrada){
                  window.location.href = url;
              }else{
                  $('#msjError').removeClass("hidden");
                  $('#msjError').text(msj);
              }

          });
                      //alert(+" || "+ $('#inpAftTkn').val());
                      //console.log($('#msjBeforeTkn').text()+" || "+ $('#inpAftTkn').val());
        //$('#formLoginToken').submit();
      });

    </script>
    <script src="../js/bootstrap.js"></script>
    
  </body>
</html>
