 <!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
<html lang="es">
<head >
<title>Ventas</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<%
ArrayList<EmpleadoBean> vendedores=(ArrayList)request.getAttribute("empleados");

String mensaje=(String)request.getAttribute("mensaje");

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
String date = sdf.format(new Date());
System.out.print(date+1);
%>
<script type="text/javascript">
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


$(document).ready(function (e) {
	  $('#myModal').on('show.bs.modal', function(e) {    
	     var id = $(e.relatedTarget).data().id;
	      $(e.currentTarget).find('#dni').val(id);
	  });
});

function fecha(){
	
	var fecha = document.getElementById("fecha");
	var f=new Date();
	var c=f.getFullYear()+"-"+(f.getMonth()+1)+"-"+(f.getDate()+1);
	
	fecha.setAttribute("max",c);
	
}

</script>
</head>
<body style="background-color: #f7f7f7;">
<jsp:include page="menu.jsp"><jsp:param name="m" value="1" /></jsp:include>
	<div class="container">
	<p align="center" style="font-size: 30px;"> Registrar Ventas </p>
		<div class="row">
			<div class="col-xs-3 col-xs-offset-8">
				<input type="text" class="form-control" placeholder="Buscar .." id="filter" />
			</div>
		</div>
		<br>
		<input type="hidden" id="accion" value="buscarV">
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
		<div class="table-responsive">
			<table class="table table-hover">
				<thead style="color:white;">
					<tr style="background-color: #36393b;">
						<td><b>DNI</b></td>
						<td><b>NOMBRE </b></td>
						<td><b>OPS</b></td>
					</tr>
				</thead>
				<tbody class="table-stripped searchable">
					<%for(int i=0;i<vendedores.size();i++){ %>
						<tr>
							<td><%=vendedores.get(i).getDni() %></td>
							<td><%=vendedores.get(i).getApellido() %> <%=vendedores.get(i).getNombre() %></td>
							<td><a href="registrarVentas.jsp" data-id="<%=vendedores.get(i).getDni()  %>" data-target="#myModal" data-toggle="modal" onclick="fecha();">Registrar Venta</a></td>
						</tr>
					<%} %>	
				</tbody>
			</table>
		</div>
	</div>
	
	
	 <!-- MODAL VENTAS -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Registrar Venta</h4>
        </div>
        <div class="modal-body">
        	 <form class="form-horizontal" role="form" method="post" action="Tipo_Empleado" id="vent">
        	 	<input type="hidden" name="dni" id="dni">
				  <div class="form-group">
				    <label class="control-label col-xs-3" for="fecha">Fecha :</label>
				    <div class="col-xs-5">
				      <input type="date" class="form-control"  id="fecha" name="fecha" max="<%=date %>" required>
				    </div>
				  </div>
				  <div class="form-group">
				  	<label  class="control-label col-xs-3" for="valor">Val Vend.</label>
				  	<div class="col-xs-5">
				  		<input type="text" class="form-control" id="valor"  name="valor" required>
				  	</div>
				  </div>
			</form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default btn-primary" type="submit" form="vent"> Agregar Venta</button>
          <input id="accion" type="hidden" name="accion" value="venta" form="vent">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
    </div>
  </div>
	
</body>
</html> 