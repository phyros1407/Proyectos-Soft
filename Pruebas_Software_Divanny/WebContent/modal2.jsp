<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/validaciones.js"></script>

<script type="text/javascript">
function perfiles(perfil,com){
	if(perfil==5){
		com.disabled=false;
	}else{
		
		com.disabled=true;
		document.getElementById("com").value="";
	}
}



function validarEspaciado(){
	
	nombre = document.getElementById("errorNombre").value;
	if(nombre.trim()==""){
		
		nombre.value=nombre.trim;
		document.getElementById("errorNomRep").innerHTML="Nombre Invalido";
		nombre.value="";
		return false;
	}
	document.getElementById("errorNomRep").innerHTML="";
	
	apellido = document.getElementById("errorApellido").value;
	if(apellido.trim()==""){
		apellido.value=apellido.trim();
		document.getElementById("errorApeRep").innerHTML="Apellido Invalido";
		return false;
	}
	
	document.getElementById("errorApeRep").innerHTML="";
	
	direccion = document.getElementById("errorDireccion").value;
	if(direccion.trim()==""){
		direccion.value=direccion.trim();
		document.getElementById("errorDirRep").innerHTML="Direccion Invalido";
		return  false;
	}
	document.getElementById("errorDirRep").innerHTML="";
	
}

function existencia(dni){
	
	
	var accion = "buscar";
	
	$.post('Empleado',{	
		dni : dni,
		accion : accion
	},function(response){
		
		var flag = response['object'];
		
		
		
		if(flag){
			document.getElementById("sub").disabled=false;
			$('#sub').removeClass('disabled');
			
			$('#senal').removeClass('glyphicon glyphicon-remove rojo');
			$('#senal').addClass('glyphicon glyphicon-ok verde');
			
		}else{
			document.getElementById("sub").disabled=true;
			$('#sub').addClass('disabled');
			$('#senal').removeClass('glyphicon glyphicon-ok verde');
			$('#senal').addClass('glyphicon glyphicon-remove rojo');
			
		}
		
	

	});
	
	
}








</script>
<style type="text/css">
	.verde{
		color:green;		
	}

	.rojo{
		color:red;
	}

</style>



</head>
<body>
<div class="modal-dialog" role="document">
    <div class="modal-content">
    <form method="post" action="Empleado" onsubmit="return validarEspaciado();">
    	<input type="hidden" name="accion" value="actualizar">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><b>Modificar Empleado</b></h4>
      </div>
      <div class="modal-body">
	      <div class="form-group col-xs-6">
		    <label for="pwd">DNI : </label>
		    <input autocomplete="off" type="text" class="form-control" maxlength="8" onpaste="return false" onkeypress="return solonumeros(event);" onkeyup="existencia(this.value);" name="dniAct" id="dniAct" required pattern=".{8,8}" title="Ingrese un DNI valido">
		 	
		  </div>
		  <div class="form-group col-xs-1" style="margin-top:28px;">
		  	<span class="" id="senal" ></span>
		  </div>
		  <div class="form-group col-xs-5">
			  	<label>Sexo</label><br>
				<label class="radio-inline"><input type="radio" name="sexoAct" value="M" required>Masculino</label>
				<label class="radio-inline"><input type="radio" name="sexoAct" value="F" required>Femenino</label>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Nombres : </label>
		    <input autocomplete="off" type="text" class="form-control" maxlength="25" onkeypress="return sololetras(event)" name="nombreAct" id="errorNombre2" required>
		    <b id="errorNomRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Apellidos : </label>
		    <input autocomplete="off" type="text" class="form-control" maxlength="25" onkeypress="return sololetras(event)" name="apellidosAct" id="errorApellido2" required>
		    <b id="errorApeRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-6">
		  	<label for="pwd">Perfil : </label>
		  	<select onchange="perfiles(this.value,comAct);" name="perfilAct" id="perfilAct" class="form-control"><option value="4">Obrero</option><option value="3">Secretario</option>
		  	<option value="5">Vendedor</option><option value="2">Contador</option><option value="1" selected>Administrador</option></select>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Comisión (en %) : </label>
		    <input autocomplete="off" type="text" id="comAct" class="form-control" maxlength="2" onkeypress="return solonumeros(event)" name="comAct" maxlength="1" required disabled >
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Direccion : </label>
		    <input autocomplete="off" type="text" class="form-control" name="direccion" maxlength="50" required id="errorDireccion2" name="direccionAct">
		  	<b id="errorDirRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Celular : </label>
		    <input autocomplete="off" type="text" class="form-control" maxlength="9" onkeypress="return solonumeros(event)"name="contactoAct" id="contactoAct1" required pattern=".{9,9}" title="Ingrese un celular valido">
		  </div>
		  <div class="form-group col-xs-6">
        	<label for="pwd">Fijo : </label>
        	<input autocomplete="off" type="text" class="form-control" name="contactoAct" id="contactoAct2" maxlength="7" onkeypress="return solonumeros(event)" required pattern=".{7,7}" title="Ingrese un numero fijo valido">
     	  </div>     	  
		  <div class="form-group col-xs-12">
		    <label for="pwd">Email : </label>
		    <input autocomplete="off" type="email" class="form-control" name="emailAct" id="emailAct" maxlength="45" required>
		  </div>
      	  <div class="form-group col-xs-6">
		    <label for="pwd">Sueldo : (S/.)</label>
		    <input autocomplete="off" type="text" value="850" class="form-control" maxlength="8" onkeypress="return solonumeros(event)" name="sueldoAct" id="sueldoAct"   maxlength="5" required>
		  </div>
		  <div class="form-group col-xs-3">
		  	<label>Seguro Medico</label>
		  	<input type="text" name="medicoAct" id="medicoAct" onkeypress="return solonumeros(event)" class="form-control" required maxlength="1">
		  </div>
		  <div class="form-group col-xs-3">
		  	<label>Seguro de Vida</label>
		  	<select class="form-control" name="seguroAct" id="seguroAct" required>
		  		<option value="1">AFP</option>
		  		<option value="2">ONP</option>
		  	</select>
		  </div>
     </div>       
	 <br/>
	 <br/>   
	 <br>   
      <div class="modal-footer">
        <button type="submit" class="loginSubmit btn btn-lg" id="sub" > Actualizar datos</button>
      </div>
      
      </form>
    </div>
  </div>
 </body>