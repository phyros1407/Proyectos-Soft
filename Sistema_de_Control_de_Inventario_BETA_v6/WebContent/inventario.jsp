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
	<script src="js/inventario.js"></script>
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/inventario.css" media="screen" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/estilo-EfrainA.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="media/css/jquery.dataTables.min.css"/>
	<title>Inventario</title>
	<style>
		.verde{
			color:green;
		}
	
		.rojo{
			color:red;
		}
		.not-active {
   			pointer-events: none;
  			cursor: default;
		}
	</style>
	<%
	Vector<ArticuloBean> articulos=(Vector)request.getAttribute("articulos");
	
		PersonaBean persona = null;
		HttpSession sesionOk=request.getSession();
		if(sesionOk.getAttribute("misesion")==null){%>
			<jsp:forward page="index.jsp">
				<jsp:param name="error" value="Debe iniciar Sesion"/>
			</jsp:forward>
		<%}else{
		
			persona=(PersonaBean)session.getAttribute("misesion");
			
			if(persona.getEstadoCuenta()!=1){%>
				<jsp:forward page="index.jsp">
					<jsp:param name="error" value="Debe iniciar Sesion"/>
				</jsp:forward>
		<%}	
		}
	%>
</head>
<body>

<div class="container">
	
	<jsp:include page="includes/nav.jsp"></jsp:include>
	
	<!-- BUSCADOR -->
	<div class="row" >
		<div class="col-xs-6"><h3><b>LISTA DE ARTICULOS GENERAL </b></h3></div>
		<div class="col-xs-4 col-xs-offset-2"><h3><jsp:include page="includes/navInvButton.jsp"></jsp:include></h3></div>
    	<div class="col-xs-2"></div>
    	<div class="col-xs-12">
    			<%
			String mensaje=(String)request.getAttribute("mensaje");
			if(mensaje!=null){
				if(mensaje.substring(0, 1).equalsIgnoreCase("Er")){%>
				<br/>
				<div class="alert alert-warning fade in"  data-dismiss="alert" aria-label="close" >
  					<b><%=mensaje.substring(0,5) %></b> <%=mensaje.substring(6) %> 
				</div>
			<%}else {%>
				<br/>
				<div class="alert alert-success fade in" data-dismiss="alert" aria-label="close" >
  					<b>Operacion exitosa!</b> <%=mensaje%> 
				</div>
			<% }}%>		
    	</div>
    </div>
<div class="table-responsive">

	<table class="table" id="inven">
		<thead>
			<tr class="danger" >
				<th>Nombre del Articulo</th>
				<th>Cantidad</th>
				<th style="text-align:center;">Medida</th>
				<th style="text-align:center;">Estado</th>
				<th>Retirar</th>
				<%if(persona.getPerfilUsuario()==1||persona.getPerfilUsuario()==2){ %>
					<th style="text-align:center;">Agregar</th>
					<th>Hab/Des</th>
				<%} %>
			</tr>
		</thead>
		<%if(persona.getPerfilUsuario()==1||persona.getPerfilUsuario()==2){ %>
		<tbody>
						
                         <%for(int i=0;i<articulos.size();i++){ %>
							<%if(articulos.get(i).getCantidad()<=0){ %>
							
								<tr>
							
							
								<td><%=articulos.get(i).getNombre() %></td>
								<td style="text-align:center;"><%=articulos.get(i).getCantidad() %></td>
								<td style="text-align:center;"><em><%=articulos.get(i).getMedida() %></em></td>
								<td style="color:gray; text-align:center;"><b>FUERA DE STOCK</b></td>
								<td style="text-align:center;" ><a href="" style="color:red;" class="not-active" data-target="#myModal" data-id1="<%=articulos.get(i).getId() %>" data-id2="<%=articulos.get(i).getNombre() %>" data-id3="<%=articulos.get(i).getCantidad() %>" data-id4="<%=articulos.get(i).getMedida() %>" data-toggle="modal"><span style="margin-top:0.5px;color:gray;"  class="glyphicon glyphicon-minus" data-placement="right" title="Retirar" ></span></a></td>
								
									<td style="text-align:center;"><a href="" data-target="#mod2" data-id="<%=articulos.get(i).getId() %>" data-name="<%=articulos.get(i).getNombre()%>" data-med="<%=articulos.get(i).getMedida()%>" data-can="<%=articulos.get(i).getCantidad()%>" data-toggle="modal"><span style="margin-top:0.5px;"  class="glyphicon glyphicon-edit" data-placement="right" title="Agregar a Stock" ></span></a></td>
									<%if(articulos.get(i).getEstado()==1){ %>
										<td style="text-align:center;">
											<a href="" data-target="#modDA" data-id="<%=articulos.get(i).getId() %>" data-toggle="modal">
												<span style="margin-top:0.5px;" class="glyphicon glyphicon-remove" data-placement="right" title="Desabilitar" ></span>
											</a>
										</td>
									<%}else{ %>
										<td style="text-align:center;">
											<a href="" data-target="#modHA" data-id="<%=articulos.get(i).getId() %>" data-toggle="modal">
												<span style="margin-top:0.5px;"  class="glyphicon glyphicon-ok" data-placement="right" title="Habilitar" ></span>
											</a>
										</td>
									<%} %>
								
							</tr>
							<%}else{%>
							<tr>
								<td><%=articulos.get(i).getNombre() %></td>
								<td style="text-align:center;"><%=articulos.get(i).getCantidad() %></td>
								<td style="text-align:center;"><em><%=articulos.get(i).getMedida() %></em></td>
								<td style="color:#0C1758; text-align:center;"><b>DISPONIBLE</b></td>
								<td style="text-align:center;"><a href="" style="color:green;" data-target="#myModal" data-id1="<%=articulos.get(i).getId() %>" data-id2="<%=articulos.get(i).getNombre() %>" data-id3="<%=articulos.get(i).getCantidad() %>" data-id4="<%=articulos.get(i).getMedida() %>" data-toggle="modal"><span style="margin-top:0.5px;color:#0C1758;"  class="glyphicon glyphicon-minus" data-placement="right" title="Retirar" ></span></a></td>
								
									<td style="text-align:center;"><a href="" data-target="#mod2" data-id="<%=articulos.get(i).getId() %>" data-name="<%=articulos.get(i).getNombre()%>" data-med="<%=articulos.get(i).getMedida()%>" data-can="<%=articulos.get(i).getCantidad()%>" data-toggle="modal"><span style="margin-top:0.5px;"  class="glyphicon glyphicon-edit" data-placement="right" title="Agregar a Stock" ></span></a></td>
									<%if(articulos.get(i).getEstado()==1){ %>
										<td style="text-align:center;">
											<a href="" data-target="#modDA" data-id="<%=articulos.get(i).getId() %>" data-toggle="modal">
												<span style="margin-top:0.5px;"  class="glyphicon glyphicon-remove" data-placement="right" title="Desabilitar" ></span>
											</a>
										</td>
									<%}else{ %>
										<td style="text-align:center;">
											<a href="" data-target="#modHA" data-id="<%=articulos.get(i).getId() %>" data-toggle="modal">
												<span style="margin-top:0.5px;"  class="glyphicon glyphicon-ok" data-placement="right" title="Habilitar" ></span>
											</a>
										</td>
								<%} %>
							</tr>
							<%} 
						}%>
		</tbody>
		<%} else{%>
		 <tbody>
						
                         <%for(int i=0;i<articulos.size();i++){ %>
                         <%if(articulos.get(i).getEstado()==1){ %>
							<%if(articulos.get(i).getCantidad()<=0){ %>
								
							<tr>
								<td><%=articulos.get(i).getNombre() %></td>
								<td style="text-align:center;"><%=articulos.get(i).getCantidad() %></td>
								<td style="text-align:center;"><em><%=articulos.get(i).getMedida() %></em></td>
								<td style="color:gray; text-align:center;"><b>FUERA DE STOCK</b></td>
								<td style="text-align:center;" >
									<a href="" style="color:red;" class="not-active" data-target="#myModal" data-id1="<%=articulos.get(i).getId() %>" data-id2="<%=articulos.get(i).getNombre() %>" data-id3="<%=articulos.get(i).getCantidad() %>" data-id4="<%=articulos.get(i).getMedida() %>" data-toggle="modal">
										<span style="margin-top:0.5px;color:gray;"  class="glyphicon glyphicon-minus" data-placement="right" title="Retirar" ></span>
									</a>
								</td>
							</tr>
							<%}else{%>
							<tr>
								<td><%=articulos.get(i).getNombre() %></td>
								<td style="text-align:center;"><%=articulos.get(i).getCantidad() %></td>
								<td style="text-align:center;"><em><%=articulos.get(i).getMedida() %></em></td>
								<td style="color:#0C1758; text-align:center;"><b>DISPONIBLE</b></td>
								<td style="text-align:center;">
									<a href="" style="color:green;" data-target="#myModal" data-id1="<%=articulos.get(i).getId() %>" data-id2="<%=articulos.get(i).getNombre() %>" data-id3="<%=articulos.get(i).getCantidad() %>" data-id4="<%=articulos.get(i).getMedida() %>" data-toggle="modal">
										<span style="margin-top:0.5px;color:#0C1758;"  class="glyphicon glyphicon-minus" data-placement="right" title="Retirar" ></span>
									</a>
								</td>
								
							</tr>
							<%} 
                         }}%>
		</tbody>
		<%} %>
	</table>		
</div>
</div>
<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/modalsInv.jsp"></jsp:include>
<jsp:include page="includes/modalMensaje.jsp"></jsp:include>


<!-- SCRIPT FORMATO DE LA TABLA -->
<jsp:include page="includes/footTable.jsp"></jsp:include>
</body>
</html>

