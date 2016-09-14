<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="es">
<head>
<%EmpleadoBean usuario=(EmpleadoBean) session.getAttribute("sesion");%>

<!-- ******************************* PARA MODAL ******************************* -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<!-- *******************************PARA MODAL  ******************************* -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 
<%
	ArrayList<EmpleadoBean> empleados = (ArrayList)request.getAttribute("empleados");
	String mensaje=(String )request.getAttribute("mensaje");
%>
 <title>Empleados</title>
<script>$(document).ready(function(){
    $("#loader").click(function(){
                // Load the page into the div
        $("#resultreturn").load("detalle.jsp");
                // Show the modal dialog
        $("#resultreturn").dialog({ modal: true });
    });
});

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
//modal eliminar
$(document).ready(function (e) { 
	$('#modE').on('show.bs.modal', function(e) { 
		var id = $(e.relatedTarget).data().id; 
		$(e.currentTarget).find('#dni').val(id); 
		}); 
	});
//BUSCAR



</script>   
<jsp:include page="menu.jsp"><jsp:param name="m" value="4" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">

<%if(usuario.getPerfilD().equalsIgnoreCase("Administrador")){ %>


<div class="container" >
	<div class="row">
		<div class="col-xs-4 col-xs-offset-2">
			<button type="button" style="float: right;margin-right: 250px;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Registrar Empleado</button>
		</div>
	</div>
	<br>
	<br>
	<div class="row">
		<div class="col-xs-3 col-xs-offset-9">
				<input type="text" class="form-control" placeholder="Buscar ..." id="filter" />
		</div>
	</div>
	
	<div class="table-responsive" >
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
		<table class="table table-hover">
		<thead style="font-weight:bold; color:white;"> 
			<tr style="background-color: #36393b;">
				<td>DNI</td>
				<td >NOMBRE</td>
				<td >CARGO</td>
				<td>ESTADO</td>
				<td>OPERACIONES</td>
			</tr>
		</thead>
		<tbody style="text-align:left;" class=" table-stripped searchable">
			<%for(int i=0;i<empleados.size();i++){ %>
			<tr>
				<td><%=empleados.get(i).getDni() %></td>
				<td><%=empleados.get(i).getNombre() %>  <%=empleados.get(i).getApellido() %></td>
				<%if(empleados.get(i).getPerfilD().equalsIgnoreCase("1")){ %>
				<td>ADMINISTRADOR</td>
				<%}else if(empleados.get(i).getPerfilD().equalsIgnoreCase("2")){ %>
				<td>CONTADOR</td>
				<%} else if(empleados.get(i).getPerfilD().equalsIgnoreCase("3")){%>
				<td>SECRETARIO(A)</td>
				<%} else if(empleados.get(i).getPerfilD().equalsIgnoreCase("4")){%>
				<td>OBRERO</td>
				<%}else{%>
				<td>VENDEDOR</td>
				<% }%>
				<%if(empleados.get(i).getEstado().equalsIgnoreCase("A")){ %>
					<td>ACTIVO</td>
					<td><a href="" data-toggle="modal" data-id="<%=empleados.get(i).getDni() %>" data-target="#modE">ELIMINAR</a></td>
				<%}else{ %>
					<td>INACTIVO</td>
					<td>ELIMINADO</td>
				<%} %>
				
			</tr>
			<%} %>	
		</tbody>	
		</table>
	</div>
</div>
<br>

<%}else{ %>
<jsp:include page="error.jsp"></jsp:include>

<%} %>

</body>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <jsp:include page="modal1.jsp"><jsp:param value="1" name="i"/> </jsp:include>
</div>

<div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <jsp:include page="modal2.jsp"><jsp:param value="1" name="i"/> </jsp:include>
</div>


<!-- Modal -->
  <div class="modal fade" id="modB" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Buscar Empleado</h4>
        </div>
        <div class="modal-body">
        	 <form class="form-horizontal" role="form">
				  <div class="form-group">
				    <label class="control-label col-sm-2" for="buscador">Dni :</label>
				    <div class="col-sm-5">
				      <input type="text" class="form-control"  placeholder="Empleado" id="buscador" pattern=".{8,8}" required title="Ingrese un dni valido">
				    </div>
				  </div>
			</form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default btn-primary" type="button"  onclick="buscar();" id="myBtn" data-dismiss="modal" >Buscar Empleado</button>
          <input id="accion" type="hidden" name="accion" value="buscar">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
    </div>
  </div>

 <div class="modal fade" id="modE" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Eliminar Empleado</h4>
        </div>
        <div class="modal-body">
        	 <form class="form-horizontal" role="form">
				  <p>¿ Esta Seguro que desea Eliminar este Empleado ? </p>
			</form>
        </div>
        <div class="modal-footer">
        <form action="Empleado" method="post">
        <button class="btn btn-default btn-primary" type="submit">Eliminar Empleado</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          <input id="accion" type="hidden" name="accion" value="eliminar">
          <input id="dni" name="dni" type="hidden">
        </form>
        </div>
      </div>
    </div>
  </div>


<!-- Js vinculados -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</html>