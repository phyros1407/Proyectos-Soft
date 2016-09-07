<!DOCTYPE html>
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
	
	<script type="text/javascript" src="tableExport.jquery.plugin-master/libs/FileSaver/FileSaver.min.js"></script>
	<script type="text/javascript" src="tableExport.jquery.plugin-master/libs/jsPDF/jspdf.min.js"></script>
	<script type="text/javascript" src="tableExport.jquery.plugin-master/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
	<script type="text/javascript" src="tableExport.jquery.plugin-master/libs/html2canvas/html2canvas.min.js"></script>
	<script type="text/javascript" src="tableExport.jquery.plugin-master/tableExport.min.js"></script>
	<script type="text/javascript" src="tableExport.jquery.plugin-master/tableExport.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/inventario.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/buscador.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/menu.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="media/css/jquery.dataTables.min.css"/>
	<title>Inventario Agotado</title>
	
	<script type="text/javascript">
		$(document).ready(function (e) {
		  $('#myModal').on('show.bs.modal', function(e) {    
		     var id = $(e.relatedTarget).data().id;
		      $(e.currentTarget).find('#codigo').val(id);
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
		
		//Exportar
		function exportar(){
			$('#reporte').tableExport({type: 'pdf',
		        jspdf: {orientation: 'p',
		            margins: {right: 20, left: 20, top: 30, bottom: 30},
		            autotable: {styles: {fillColor: 'inherit', 
		                                 textColor: 'inherit',
		                                 fontStyle: 'inherit'},
		                        tableWidth: 'wrap'}
		            }
			});
		}
	</script>
	
	<%
		Vector<ArticuloBean> articulosA=(Vector)request.getAttribute("articulosA");
	%>

</head>
<body>
	<div class="container" id="reporte">
		<jsp:include page="includes/nav.jsp"></jsp:include>
		<br/>
		
			<div class="table-responsive" >
				<table class="table">
					<thead>
						<tr>
							<th colspan="2" style="text-align:center; font-size: 26px;" >REPORTE DE STOCK AGOTADO</th>
							<td>
								<input type=image src="imagenes/pdf-icon.png" width="50" height="50" onclick="exportar();">
							</td>
						</tr>
					</thead>
					<tbody >
						<tr class="danger" >
							<td style="font-weight:bold;" data-tableexport-msonumberformat="\@">Nombre del Articulo</td>
							<td style="font-weight:bold;" data-tableexport-msonumberformat="\@"><b>Medida</b></td>
							<td style="text-align:center; font-weight:bold;" data-tableexport-msonumberformat="\@"><b>Stock</b></td>
						</tr>
					<%for(int i=0;i<articulosA.size();i++){ %>
						<tr>
							<td><%=articulosA.get(i).getNombre() %></td>
							<td  data-tableexport-msonumberformat="\@"><em><%=articulosA.get(i).getMedida() %></em></td>
							<td style="text-align:center;" data-tableexport-msonumberformat="\@"><%=articulosA.get(i).getCantidad() %></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
		
	</div>
	
	<jsp:include page="includes/footer.jsp"></jsp:include>
	<jsp:include page="includes/modalMensaje.jsp"></jsp:include>
	<!-- SCRIPT FORMATO DE LA TABLA -->
	<jsp:include page="includes/footTable.jsp"></jsp:include>
</body>
</html>

