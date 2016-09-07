//Pasa datos al Modal --- mod
$(document).ready(function (e) {
  $('#mod').on('show.bs.modal', function(e) {    
     var id = $(e.relatedTarget).data().id;
      $(e.currentTarget).find('#codigo2').val(id);
  });
});

//Pasa datos al Modal --- mod1
$(document).ready(function (e){
	$('#mod1').on('show.bs.modal',function(e){
		var id=$(e.relatedTarget).data().id;
		$(e.currentTarget).find('#codigo2').val(id);
	});
});



//Pasa datos al Modal --- mod3
$(document).ready(function (e) {
  $('#mod3').on('show.bs.modal', function(e) {    
     var id = $(e.relatedTarget).data().id;
     var name = $(e.relatedTarget).data().name;
     var lastname = $(e.relatedTarget).data().lastname;
     var mail = $(e.relatedTarget).data().mail;
     var phone = $(e.relatedTarget).data().phone;
     var per=$(e.relatedTarget).data().per;
    
     var slastname=lastname.substr(0,lastname.indexOf(" "));
     var mlastname=lastname.substr(lastname.indexOf(" "));
     
      $(e.currentTarget).find('#codigo3').val(id);
      $(e.currentTarget).find('#nombre').val(name);
      $(e.currentTarget).find('#apeP').val(slastname.trim());
      $(e.currentTarget).find('#apeM').val(mlastname.trim());
      $(e.currentTarget).find('#correo').val(mail);
      $(e.currentTarget).find('#celular1').val(phone);
      $(e.currentTarget).find('#sel2').val(per);
      
  });
});




//Esto Busca
$(document).ready(function () {
    (function ($) {
        $('#filter').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.searchable tr').hide();
            $('.searchable tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        })
    }(jQuery));
});



//VALIDAR CAMPOS

function validar(){
	//celular
	valor = document.getElementById("celular").value;
	if( !(/^\d{9}$/.test(valor)) ) {
		document.getElementById("errorC").innerHTML="Numero  Ingresado Incorrecto";
  		return false;
	}
		document.getElementById("errorC").innerHTML="";
		
	
	
	indice = document.getElementById("sel1").selectedIndex;
	if( indice == null || indice == 0 ) {
		document.getElementById("errorS").innerHTML="Debe seleccionar un Permiso";
  		return false;
	}
		document.getElementById("errorS").innerHTML="";
		
	
}


function validar2(){
	//celular
	valor = document.getElementById("celular1").value;
	if( !(/^\d{9}$/.test(valor)) ) {
		document.getElementById("errorC1").innerHTML="Numero  Ingresado Incorrecto";
  		return false;
	}
		document.getElementById("errorC1").innerHTML="";
		
	
	
	indice = document.getElementById("sel2").selectedIndex;
	if( indice == null || indice == 0 ) {
		document.getElementById("errorS1").innerHTML="Debe seleccionar un Permiso";
  		return false;
	}
		document.getElementById("errorS1").innerHTML="";
		
	
}






