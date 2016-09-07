<!DOCTYPE html>
<%@page import="beans.MovimientoBean"%>
<%@page import="beans.PersonaBean"%>
<%@page import="beans.ArticuloBean"%>
<%@page import="java.util.Vector"%>
<%@page session="true" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="css/bootstrap.min.js"></script>
	<script src="js/usuario.js"></script>
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/inventario.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/buscador.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="media/css/jquery.dataTables.min.css"/>
	<title>misMovimientos</title>
	
	<% Vector<MovimientoBean>movimientos=(Vector)request.getAttribute("movimientos"); %>
	
	<script type="text/javascript">
		function detalles(idM){
			
			var ph="detalle";
			
			
			
			$.post('Movimiento',{	
				mov : idM,
				ph : ph
			},function(response){
				
				var nomA = response['object']['nombreArticulo'];
				var medida = response['object']['medida'];
				var cantidad = response['object']['cantidad_retirada'];
				var fecha = response['object']['fecha_mov'];
				var observacion = response['object']['comientario'];
				
				$('#articuloMos').text(nomA);
				$('#medidaMos').text(medida);
				$('#cantidadMos').text(cantidad);
				$('#fechaMos').text(fecha);
				$('#obsMos').text(observacion.trim());
				
			});
		}
	</script>
</head>
<body>
	<div class="container">
		<jsp:include page="includes/nav.jsp"></jsp:include>
		<!-- BUSCADOR -->
		<div class="row" >
			<div class="col-xs-4  col-xs-offset-1">
				<h3><b>MIS MOVIMIENTOS</b></h3>
			</div>
			<div class="col-xs-4"></div>
			<div class="col-xs-4  col-xs-offset-2"> 
	    	</div>
	   </div>
		<br/>
		<form action="" method="get"> 
			<div class="table-responsive">
				<table class="table" id="inven">
					<thead>
						<tr class="danger" >
							<th>Articulo Retirado</th>
							<th>Cantidad Retirada</th>
							<th>Fecha del Movimiento </th>
							<th>Detalle</th>
						</tr>
					</thead>
					<tbody class="table-striped searchable">
			
					<%for(int i=0;i<movimientos.size();i++){ %>
						<tr>
							<td><%=movimientos.get(i).getNombreArticulo() %> (<em><%=movimientos.get(i).getMedida()%></em>)<input type="hidden" name="articulo" value="<%=movimientos.get(i).getId_articulo()%>"></td>
							<td style="text-align:center;"><%=movimientos.get(i).getCantidad_retirada() %></td>
							<td><%=movimientos.get(i).getFecha_mov() %></td>
							<td><a href="" onclick="detalles(<%=movimientos.get(i).getId()%>);" data-target="#modDetM" data-toggle="modal" data-id="<%=movimientos.get(i).getId()%>">Ver</a></td>
						</tr>
					<%} %>
					
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<jsp:include page="includes/footer.jsp"></jsp:include>
	<jsp:include page="includes/modalMensaje.jsp"></jsp:include>
	<jsp:include page="includes/modalMovimiento.jsp"></jsp:include>
	
	<!-- SCRIPT FORMATO DE LA TABLA -->
	<jsp:include page="includes/footTable.jsp"></jsp:include>
</body>
</html>

