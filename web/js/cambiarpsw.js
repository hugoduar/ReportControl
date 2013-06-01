$('#btnCambiar').click(function(){
        var Pass = $('#inputPass').val();
        var PassNew = $('#inputPassNew').val();
        var PassNewRepeat = $('#inputPassNewRep').val();
        $.post('../CambiarPassword',
          {inputPass:Pass,
          inputPassNew:PassNew,
          inputPassNewRep:PassNewRepeat},
          function(data) {
              var dataJSON = $.parseJSON(data);
              $.each(dataJSON, function(index, element) {
                  mensaje = element.mensaje;
                });
              $('#msjError').removeClass("hidden");
              $('#msjError p').html(mensaje);      
    });
});