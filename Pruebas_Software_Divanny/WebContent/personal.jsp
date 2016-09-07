<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal</title>
<!-- ******************************* PARA MODAL ******************************* -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<!-- ******************************* PARA MODAL ******************************* -->
</head>
<body style="background-color: #f7f7f7;">
<div align="center">
<jsp:include page="menu.jsp"><jsp:param name="m" value="4" /></jsp:include>
</div>
<button type="button" style="margin: 0 auto;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Registrar Empleado</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form method="post" action="<%=request.getContextPath()%>/AgregarCliente">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Datos Del Empleado</h4>
      </div>
      <div class="modal-body">
	       <div class="form-group col-xs-12">
		    <label for="email">DNI : </label>
		    <input type="text" class="form-control" maxlength="8" onkeypress="return solonumeros(event)" name="dni"  required>
		  </div>
		  <div class="form-group col-xs-12 ">
		    <label for="pwd">Nombres : </label>
		    <input type="text" class="form-control" onkeypress="return sololetras(event)" name="nombre" required>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Apellidos : </label>
		    <input type="text" class="form-control" onkeypress="return sololetras(event)" name="apellidos" required>
		  </div>
		  <div class="form-group col-xs-6">
		  	<label for="pwd">Perfil : </label>
		  	<select name="perfil" class="form-control"><option value="obrero">Obrero</option>
		  	<option value="vendedor">Vendedor</option><option value="contador">Contador</option><option value="secretario">Secretario</option></select>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Comisión (en %) : </label>
		    <input type="text" class="form-control" name="comision" required>
		  </div>
		  <div class="form-group col-xs-12">
		    <label for="pwd">Direccion : </label>
		    <input type="text" class="form-control" name="direccion" required>
		  </div>
		  <div class="form-group col-xs-6">
		    <label for="pwd">Celular : </label>
		    <input type="text" class="form-control" maxlength="9" onkeypress="return solonumeros(event)"name="celular" required>
		  </div>
		  <div class="form-group col-xs-6">
        	<label for="pwd">Fijo : </label>
        	<input type="text" class="form-control" name="fijo"  maxlength="7" onkeypress="return solonumeros(event)" required>
     	  </div>     	  
		  <div class="form-group col-xs-12">
		    <label for="pwd">Email : </label>
		    <input type="email" class="form-control" name="email" required>
		  </div>
      	  <div class="form-group col-xs-6">
		    <label for="pwd">Sueldo : </label>
		    <input type="text" class="form-control" name="email" required>
		  </div>
      	  </div>      
         <div class="form-group col-xs-6">
        	<label >Sexo:</label><br>         
			    <input type="radio" name="sexo" value="M" required> Masculino&nbsp			          
			    <input type="radio" name="sexo" value="F" required> Femenino				
		  </div>		  
		  	<br/>
			<br/>   
			<br>   
      <div class="modal-footer">
        <input type="submit" class="loginSubmit" value="Registrar Cliente">
      </div>
      
      </form>
    </div>
  </div>
</div>
<!-- Js vinculados -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>