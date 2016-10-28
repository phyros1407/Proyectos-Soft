<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%EmpleadoBean usuario=(EmpleadoBean) session.getAttribute("sesion");%>
<meta charset="utf-8" http-equiv="Content-Type"  content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/ajax_call2.js" charset="UTF-8"></script>
<%String mensaje=(String)request.getAttribute("mensaje"); %>
<title>Seguro</title>
</head>
<body style="background-color: #f7f7f7;overflow-x: hidden;">
<script>
function esconder(){
	$('#submit').css("visibility", "hidden")
}
</script>



	<jsp:include page="menu.jsp"><jsp:param name="m" value="1" /></jsp:include>
	<%if(usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
	<div class="container">
		<div class="col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
		<%if(mensaje!=null){ %>
				<%if(mensaje.substring(0,1).equalsIgnoreCase("R")) {%>
					<div class="alert alert-success fade in col-xs-7 col-md-7 ">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  			<strong><%=mensaje %></strong>
					</div>
				<%}else{ %>
					<div class="alert alert-warning fade in col-xs-7 col-md-7">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  			<strong><%=mensaje %></strong>
					</div>
				<%} %>
			<%} %>
		</div>
		<!-- CUERPO -->
			<div class="form-group col-xs-12 col-md-12 col-xs-offset-8 col-md-offset-8">	
				<div class="col-xs-6 col-md-6 ">
					<button class="btn btn-default" type="button" data-target="#myModal" data-toggle="modal" onclick="esconder()" >Buscar Empleado</button>
				</div>
			</div>
			<br>
			<br>
			
			<form method="post" action="Seguro">
			<input type="hidden" name="accion" value="modificar">
			<input type="hidden" name="dni" id="dni_t">
				  <div class="form-group col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
				  	<div class="col-xs-3 col-md-3">
				  		<label class="control-label col-xs-2 col-md-2" for="dni" style ="color:#08088A; text-align:left;"><b>DNI:</b></label>
				  	</div>
				    <div class="col-xs-6 col-md-6">
				      <label id="dni"></label>
				    </div>
				  </div>
				  
				  
				  <div class="form-group col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
				 	<div class="col-xs-3 col-md-3">
				    	<label class="control-label col-xs-2 col-md-2" for="nombre" style ="color:#08088A;text-align:left;"><b>NOMBRE:</b></label>
				    </div>
				    <div class="col-xs-6 col-md-6">
				     <label id="nombre"></label>
				    </div>
				  </div>
				  <div class="form-group col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
				  	<div class="col-xs-3 col-md-3"> 
				  		<label class="control-label col-xs-2 col-md-2" for="apellidos" style ="color:#08088A;text-align:left;"><b>APELLIDO:</b></label>
				    </div>
				    <div class="col-xs-6 col-md-6">
				    	<label id="apellidos"></label>
				    </div>
				  </div>
				  <div class="form-group col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
				  	<div class="col-xs-3 col-md-3">
				    	<label class="control-label col-xs-2 col-md-2" for="seguro" style ="color:#08088A;text-align:left;"><b>SEGURO:</b></label>
				    </div>
				    <div class="col-xs-6 col-md-6">
				      <label id="seguro"></label>
				    </div>
				  </div>
				  <div class="form-group col-xs-12 col-md-12 col-xs-offset-3 col-md-offset-3">
				  	<div class="col-xs-3 col-md-3">
				    	<label class="control-label col-xs-2 col-md-2" for="seguro" style ="color:#08088A;text-align:left;"><b>PORCENTAJE:</b></label>
				    </div>
				    <div class="col-xs-2 col-md-2">
				      <input type="number" id="porcentaje" step="any" name="porcentaje" disabled style="width:60%" max="80" min="1" required>
				    </div>
				    <br>
				    <br>
				  </div>
				  
				  <p align="center"><input type="submit" id="submit" value="Guardar cambios" class="btn btn-default btn-primary" style="visibility: hidden"></p>
			</form>
			
	</div>
	
<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Buscar Empleado</h4>
        </div>
        <form class="form-horizontal" role="form">
        <div class="modal-body">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="buscador">Dni :</label>
				    <div class="col-sm-5">
				    <input maxlength="8" pattern=".{8}" required title="Ingrese 8 dígitos" id="txtDNI"
								onkeypress="return solonumeros(event)" name="txtDNI" data-required="1" class="form-control" >
				    </div>
				  </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default btn-primary" type="button" data-target="#myModal" data-toggle="modal" onclick="cargar2();" >Buscar Empleado</button>
          <input id="accion" type="hidden" name="accion" value="buscarA">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>          
        </div>
        </form>
      </div>
    </div>
  </div>

<%}else{ %>
 <jsp:include page="error.jsp"></jsp:include>
 <%} %>
</body>
</html>