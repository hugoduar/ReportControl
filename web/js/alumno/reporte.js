$('#btnAut').click(function(){
        $.post('../AgregarReporte',
          {txtDescReporte:$('#txtDescReporte').val(),
          inputNumero:$('#inputNumero').val(),
          optionFalla:$('input[name=optionFalla]:checked', '#formReporte').val(),
          inputDesFalla:$('#inputDesFalla').val(),
          inputAsignatura:$('#inputAsignatura').val(),
          inputNickProfesor:$('#inputNickProfesor').val(),
          inputPasswordProfesor:$('#inputPasswordProfesor').val()},
          function(data) {
              var dataJSON = $.parseJSON(data);
              $.each(dataJSON, function(index, element) {
                  mensaje = element.mensaje;
                });
              $('#msjError').removeClass("hidden");
              $('#msjError p').html(mensaje);               
    });

});


