
<script type="text/javascript">

function validacionCompleta(){
	
	
	var p1 = document.getElementById("passUsu").value;
	var p2 = document.getElementById("passUsu2").value;
	var valor = document.getElementById("telefonoUsu").value;
  	if (p1 != p2) {
  		document.getElementById("errorC1").innerHTML="Las contraseñas deben coincidir";
  		return false;
	}
  	document.getElementById("errorC1").innerHTML="";
  	
	if( !(/^\d{9}$/.test(valor)) ) {
		document.getElementById("errorC").innerHTML="Numero  Ingresado Incorrecto";
  		return false;
	}
		document.getElementById("errorC").innerHTML="";
		
	
	var pass=document.getElementById("passUsu").value;
	var pass2=document.getElementById("passUsu2").value;
		
	if(pass=="        "||pass=="         "||pass=="          "||pass=="           "||pass=="            "||pass=="             "){
		document.getElementById("errorC1").innerHTML="La contraseña ingresada es invalida";
		return false;
	}
	if(pass2=="        "||pass2=="         "||pass2=="          "||pass2=="           "||pass2=="            "||pass2=="             "){
		document.getElementById("errorC1").innerHTML="La contraseña ingresada es invalida";
		return false;
	}	
		
	return true;
	
	
	
}

function validar(e) { 
	//VALIDA SOLO ENTRADA DE NUMEROS
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
   	patron = /\d/; 
    te = String.fromCharCode(tecla); 
    return patron.test(te); 
}



function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
         if(key == especiales[i]){
             tecla_especial = true;
             break;
         }
     }

     if(letras.indexOf(tecla)==-1 && !tecla_especial){
         return false;
     }
 }
 

</script>

<!-- MODAL CONFIGURACION-->
<div id="modConf" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
     <div class="modal-header" style="background-color:#8A0829;">
          <button type="button" class="close" data-dismiss="modal" style="color:white;">&times;</button>
          <h3 class="modal-title" style="color:#FFFFFF;"><span class="glyphicon glyphicon-cog" style="width: 31px; height: 29px; "  ></span><b>Actualizar Informacion</b></h3>
        </div>
      <div class="modal-body" style="height: 350px;">
        <form action="Persona" method="post" id="cofig" onsubmit="return validacionCompleta();">
        	<input type="hidden" value="config" name="ph">
        	<input type="hidden" id="codU" name="codU">
        	<div class="form-group col-xs-6">
        		<label for="apellidoUsu">Apellido Paterno: </label>
        		<input type="text" class="form-control" id="apellidoPat" name="apellidoPat" required onkeypress="return soloLetras(event)">
        	</div>
        	<div class="form-group col-xs-6">
        		<label for="apellidoUsu">Apellido Materno: </label>
        		<input type="text" class="form-control" id="apellidoMat" name="apellidoMat" required onkeypress="return soloLetras(event)">
        	</div>
        	<div class="form-group col-xs-12">
        		<label for="nombreUsu">Nombres : </label>
        		<input type="text" class="form-control" id="nombreUsu" name="nombreUsu" required onkeypress="return soloLetras(event)">
        	</div>
        	<div class="form-group col-xs-6">
        		<label for="correoUsu">Correo :</label>
        		<input type="email" class="form-control" id="correoUsu"  disabled>
        	</div>
        	<div class="form-group col-xs-6">
        		<label for="telefonoUsu">Teléfono :</label>
        		<input type="text"  class="form-control" id="telefonoUsu" name="telefonoUsu" maxlength="9" required onkeypress="return validar(event)">
        		<b id="errorC" style="color:red;"></b>
        	</div>
        	<div class="form-group col-xs-12">
        		<label for="passUsu">Nueva contraseña : </label>
        		<input type="password" class="form-control" id="passUsu" name="passUsu" pattern=".{8,13}"   title="Ingrese contraseña de 8 a 13 caracteres" >
        		<b id="errorC1" style="color:red;"></b>
        	</div>
        	<div class="form-group col-xs-12">
        		<label for="passUsu2">Confirmar contraseña : </label>
        		<input type="password" class="form-control" id="passUsu2" name="passUsu2" pattern=".{8,13}"  title="Ingrese contraseña de 8 a 13 caracteres">
        	</div>
        	<div class="form-group col-xs-12">
        		<label>*Longitud de la contraseña <b>(mín 8 máx 13)</b></label>
        	</div>
        </form>
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary" form="cofig">Actualizar</button>
        <button type="button"  class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>

  </div>
</div>