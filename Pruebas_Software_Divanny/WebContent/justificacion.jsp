<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8" http-equiv="Content-Type"  content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<%String mensaje=(String)request.getAttribute("mensaje"); %>
<title>Justificacion</title>
<script type="text/javascript">
			  var fechas=[];
			  function cargar(){
					var dniJ = $('#txtDNI').val();	
					var accion=$('#accion').val();
					$.post('Justificacion',{	
						dniJ : dniJ,
						accion : accion
					},function(response){
						
						
						if(response['object']==null){
							alert("Empleado no encontrado");
						}
						
						var dni=response['object']['dni'];
						var nombre=response['object']['nombre'];
						var apellido=response['object']['apellido'];
						var residencia=response['object']['residencia'];
						var conta="";
						fechas=new Array(response['object']['asis'].length);
						for(var i=0;i<response['object']['asis'].length;i++){
							fechas[i]=response['object']['asis'][i]['id'];
							conta=conta+("<option value='"+response['object']['asis'][i]['id']+"'>"+response['object']['asis'][i]['horaEnt']+"</option>");
						}
						
						

						$('#dni').text(dni);
						$('#dni_t').val(dni);
						$('#nombre').text(nombre);
						$('#apellidos').text(apellido);
						$('#residencia').text(residencia);
						$('#fechas').html(conta);
						
						if(fechas[0]==null){

							//window.location.href ="justificacion.jsp";
							alert("Error: Faltas injustificadas no encontradas");
						}

											});
			}
</script> 
</head>
<body style="background-color: #f7f7f7;">
<jsp:include page="menu.jsp"><jsp:param name="m" value="1" /></jsp:include>
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4">
			<%if(mensaje!=null){ %>
				<%if(mensaje.substring(0,1).equalsIgnoreCase("R")) {%>
					<div class="alert alert-success fade in">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  			<strong><%=mensaje %></strong>
					</div>
				<%}else{ %>
					<div class="alert alert-warning fade in">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  			<strong><%=mensaje %></strong>
					</div>
				<%} %>
			<%} %>
		</div>
		<div class="col-xs-4"></div>
	</div>
	
	<div class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-7">
		
		<!-- CUERPO -->
				<div class="form-group">	
					<div class="col-xs-4 col-xs-offset-5">
						<button class="btn btn-default" type="button" data-target="#myModal" data-toggle="modal"  >Buscar Empleado</button>
					</div>
				</div>
			<br>
			<br>
			 <form class="form-horizontal" role="form">
				  <div class="form-group">
				    <label class="control-label col-xs-2" for="dni" style ="color:#08088A;text-align:left;"><b>DNI:</b></label>
				    <div class="col-xs-10">
				      <label id="dni"></label>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-xs-2" for="nombre" style ="color:#08088A;text-align:left;"><b>NOMBRE:</b></label>
				    <div class="col-xs-10">
				     <label id="nombre"></label>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-xs-2" for="apellidos" style ="color:#08088A;text-align:left;"><b>APELLIDO:</b></label>
				    <div class="col-xs-10">
				    <label id="apellidos"></label>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-xs-2" for="residencia" style ="color:#08088A;text-align:left;"><b>RESIDENCIA:</b></label>
				    <div class="col-xs-10">
				      <label id="residencia"></label>
				    </div>
				  </div>
			</form>
			 <br>
			<form action="Justificacion" method="post" id="form1" enctype="multipart/form-data" class="form-horizontal">
			  	<input type="hidden"  id="dni_t"  name="dni_t"/>
			  	<input type="hidden" name="accion" value="justificar"/>
			  	<div class="form-group">
			  		<div class="col-xs-3">
			  			<label>Fecha a justificar:</label>
			  			<select name="asis" class="form-control" id="fechas" required>
			  				
			  			</select>
			  		</div>	
			  	</div>
			  	<div class="form-group">
			  		<div class="col-xs-3">
				  		<label>Motivo :</label>
				  		<select name="motivo" class="form-control" required>
				  				<option value="Enfermedad">Enfermedad</option>
				  				<option value="Personales">Personales</option>
				  		</select>
			  		</div>
			  	</div>
			  	<div class="form-group">
			  		<div class="col-xs-4">
			  			<label>Seleccione el doc/archivo a subir :</label>
			  			<br>
			  			<input type="file" name="archivo"  size="50" required>
			  		</div>
			  	</div>
			  	<div class="form-group">
			  		<div class="col-xs-12">
			  			<textarea rows="5" cols="80" class="form-control" style="resize:vertical; max-height:150px; min-height:150px;" name="comentario" required placeholder="Comentario ..."></textarea>
			  		</div> 
			  	</div>
			  	
			  	<div class="form-group">
			  		<div class="col-xs-3 col-xs-offset-5">
			  			<button type="submit" id="btn_just" class="btn btn-default btn-success">Registrar Justificacion</button>
			  		</div>
			  	</div>
			  	</form>
			  	
		</div>
		<div class="col-xs-1"></div>
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
				    <input maxlength="8" pattern=".{8,8}" required title="Ingrese 8 dígitos" id="txtDNI"
								onkeypress="return solonumeros(event)" name="txtDNI" data-required="1" class="form-control">
				    </div>
				  </div>
			
        </div>
        <div class="modal-footer">
          <button class="btn btn-default btn-primary" type="button" data-target="#myModal" data-toggle="modal" onclick="cargar();" >Buscar Empleado</button>
          <input id="accion" type="hidden" name="accion" value="buscar">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>          
        </div>
        </form>
      </div>
    </div>
  </div>

	
			   
</body>
</html>