<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/validaciones.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function perfiles(perfil,com){
	if(perfil==5){
		com.disabled=false;
	}else{
		
		com.disabled=true;
		document.getElementById("com").value="";
	}
}


function validarEspaciado(nombre,apellido,direccion){
	
	
	if($("#errorNombre").trim()==""){
		$("#errorNomRep").text("Nombre Invalido");
		return false;
	}
	if($("#errorApellido").trim()==""){
		$("#errorApeRep").text("Apellido Invalido");
		return false;
	}
	if($("#errorDireccion").trim()==""){
		$("#errorDirRep").text("Direccion Invalido");
		return  false;
	}
	
}


</script>

</head>
<body>
<%int i=Integer.parseInt(request.getParameter("i")); switch(i){case 1: %>
<div class="modal-dialog" role="document">
    <div class="modal-content">
    <form method="post" action="Empleado" onsubmit="return validarEspaciado()">
    	<input type="hidden" name="accion" value="registrar">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><b>Datos Del Empleado</b></h4>
      </div>
      <div class="modal-body">
	      <div class="form-group col-xs-6">
		    <label for="pwd">DNI : </label>
		    <input type="text" class="form-control" maxlength="8" onkeypress="return solonumeros(event)" name="dni"  required pattern=".{8,8}" title="Ingrese un dni valido">
		  </div>
		  <div class="form-group col-xs-6">
			  	<label>Sexo</label><br>
				<label class="radio-inline"><input type="radio" name="sexo" value="M" required>Masculino</label>
				<label class="radio-inline"><input type="radio" name="sexo" value="F" required>Femenino</label>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Nombres : </label>
		    <input type="text" class="form-control" maxlength="25" onkeypress="return sololetras(event)" name="nombre" id="errorNombre" required>
		    <b id="errorNomRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Apellidos : </label>
		    <input type="text" class="form-control" maxlength="25" onkeypress="return sololetras(event)" name="apellidos" id="errorApellido" required>
		    <b id="errorApeRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-6">
		  	<label for="pwd">Perfil : </label>
		  	<select onchange="perfiles(this.value,com);" name="perfil" id="perfil" class="form-control"><option value="4">Obrero</option><option value="3">Secretario</option>
		  	<option value="5">Vendedor</option><option value="2">Contador</option><option value="1" selected>Administrador</option></select>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Comisión (en %) : </label>
		    <input type="text" id="com" class="form-control" maxlength="2" onkeypress="return solonumeros(event)" name="comision" maxlength="2" required disabled >
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Direccion : </label>
		    <input type="text" class="form-control" name="direccion" maxlength="50" required id="errorDireccion">
		  	<b id="errorDirRep" style="color:red;"></b>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Celular : </label>
		    <input type="text" class="form-control" maxlength="9" onkeypress="return solonumeros(event)"name="contacto" required pattern=".{9,9}" title="Ingrese un celular valido">
		  </div>
		  <div class="form-group col-xs-6">
        	<label for="pwd">Fijo : </label>
        	<input type="text" class="form-control" name="contacto" maxlength="7" onkeypress="return solonumeros(event)" required pattern=".{7,7}" title="Ingrese un numero fijo valido">
     	  </div>     	  
		  <div class="form-group col-xs-12">
		    <label for="pwd">Email : </label>
		    <input type="email" class="form-control" name="email" maxlength="45" required>
		  </div>
      	  <div class="form-group col-xs-6">
		    <label for="pwd">Sueldo : </label>
		    <input type="text" class="form-control" maxlength="8" onkeypress="return solonumeros(event)" name="sueldo"   maxlength="5" required>
		  </div>
		  <div class="form-group col-xs-3">
		  	<label>Seguro Medico</label>
		  	<input type="text" name="medico" onkeypress="return solonumeros(event)" class="form-control" required maxlength="1">
		  </div>
		  <div class="form-group col-xs-3">
		  	<label>Seguro de Vida</label>
		  	<select class="form-control" name="seguro" required>
		  		<option value="1">AFP</option>
		  		<option value="2">ONP</option>
		  	</select>
		  </div>
     </div>       
	 <br/>
	 <br/>   
	 <br>   
      <div class="modal-footer">
        <input type="submit" class="loginSubmit" value="Registrar Empleado">
      </div>
      
      </form>
    </div>
  </div>
<%; break;case 2: %>
<div class="modal-dialog" role="document">
    <div class="modal-content">
    <form method="post" action="RegistrarEmpleado">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Datos Del Usuario</h4>
      </div>
      <div class="modal-body">
		  <div class="form-group col-xs-12 ">
		    <label for="pwd">Usuario : </label>
		    <input type="text" class="form-control" onkeypress="return sololetras(event)" name="nombre" required>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Contraseña : </label>
		    <input type="text" class="form-control" onkeypress="return sololetras(event)" name="apellidos" required>
		  </div>
      	  </div>      	  
		  	<br/>
			<br/>   
			<br>   
      <div class="modal-footer">
        <input type="submit" class="loginSubmit" value="Guardar cambios">
      </div>
      
      </form>
    </div>
  </div>
<%; break;}%>

</body>
</html>