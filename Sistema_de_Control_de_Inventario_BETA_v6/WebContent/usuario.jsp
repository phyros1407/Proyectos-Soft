<!DOCTYPE html>
<%@page import="beans.PersonaBean"%>
<%@page import="beans.UsuarioBean"%>
<%@page import="java.util.Vector"%>
<%@page session="true" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="js/usuario.js"></script>
	
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/inventario.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="css_no_bootstrap/buscador.css" media="screen" />
	
	<title>Usuario</title>
	<% Vector<PersonaBean> personas=(Vector)request.getAttribute("personas"); %>
	
	<%

		PersonaBean persona = null;
			HttpSession sesionOk=request.getSession();
			if(sesionOk.getAttribute("misesion")==null){
				response.sendRedirect("Sistema");
			}else{
			
				persona=(PersonaBean)session.getAttribute("misesion");
				
				if(persona.getEstadoCuenta()!=1){%>
					<jsp:forward page="index.jsp">
						<jsp:param name="error" value="Debe iniciar Sesion"/>
					</jsp:forward>
				
				<%}	
			} 
			
		    int pre = (int) (Math.random()*990+100);
		    int post =  (int) (Math.random()*990+100);
		    System.out.println("pre : "+pre);
		    System.out.println("post : "+post);
		    String preS=Integer.toString(pre);
		    String postS=Integer.toString(post);
    		
	%>
	
	
	<script>
		    function soloLetras(e){
		       key = e.keyCode || e.which;
		       tecla = String.fromCharCode(key).toLowerCase();
		       letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
		       especiales = "8-37-39-46";
		
		       tecla_especial = false
		       for(var i in especiales){
		            if(key == especiales[i]){
		                tecla_especial = true;
		                break;
		            }
		        }
		
		        if(letras.indexOf(tecla)==-1 && !tecla_especial){
		            return false;
		        }
		    }
		    
	</script>
	
	<style>
		.not-active {
		   			pointer-events: none;
		  			cursor: default;
				}
	</style>

</head>
<body>
	<div class="container">
		<jsp:include page="includes/nav.jsp"></jsp:include>
		<br>
		<!-- BUSCADOR -->
		<div class="row" >
			<div class="col-xs-12">
	    			<%
					String mensaje=(String)request.getAttribute("mensaje");
					if(mensaje!=null){
						if(mensaje.substring(0, 1).equalsIgnoreCase("E")){%>
					
						<div class="alert alert-warning fade in"  data-dismiss="alert" aria-label="close" >
		  					<b><%=mensaje.substring(0,6) %></b> <%=mensaje.substring(6) %> 
						</div>
					<%}else {%>
						
						<div class="alert alert-success fade in" data-dismiss="alert" aria-label="close" >
		  					<b>Operacion exitosa!</b> <%=mensaje%> 
						</div>
					<% }
					}%>		
	    	</div>
			<div class="col-xs-4  col-xs-offset-1">
				<div class="left-inner-addon">
	        		<i class="glyphicon glyphicon-search"></i>
	        		<input id="filter" type="text" class="form-control" placeholder="Buscar..." />
	    		</div>
			</div>
			<div class="col-xs-4"></div>
			<div class="col-xs-4  col-xs-offset-2" id="usuarios" style="text-align:center;"> 
				<a href="" class="btn btn-success " data-target="#mod2" data-toggle="modal" >Agregar Usuario</a>
	    	</div>
	   </div>
		<div class="table-responsive">
			<br/>
			<table class="table table-hover table-bordered" id="tab">
				<thead>
					<tr >
						<th style="text-align:center;">Nombre Completo</th>
						<th style="text-align:center;">Correo</th>
						<th style="text-align:center;">Telefono</th>
						<th style="text-align:center;">Modificar</th>
						<th style="text-align:center;">Act./Des.</th>	
					</tr>
				</thead>
				<tbody class="table-striped searchable">
				<%for(int i=0;i<personas.size();i++){ 
					if(personas.get(i).getEstadoCuenta()==1){
					if(persona.getId()==personas.get(i).getId()){%>
					<tr class="success">
						<td><%=personas.get(i).getApellido() %> <%=personas.get(i).getNombre() %></td>
						<td style="text-align:center;"><%=personas.get(i).getCorreo() %></td>
						<td style="text-align:center;"><%=personas.get(i).getTelefono() %></td>
						<td style="text-align:center;">
							<a href="" data-target="#mod3" data-toggle="modal" data-placement="right" title="Modificar Account" data-id="<%=personas.get(i).getId() %>" data-name="<%=personas.get(i).getNombre() %>" data-lastname="<%=personas.get(i).getApellido() %>" data-mail="<%=personas.get(i).getCorreo() %>" data-phone="<%=personas.get(i).getTelefono()%>" data-per="<%=personas.get(i).getPerfilUsuario()%>">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-edit" ></span>
							</a>
						</td>
						<td style="text-align:center;">
							<a href="" data-target="#mod"  class="not-active" data-toggle="modal" data-id="<%=personas.get(i).getId()%>" data-placement="right" title="Desactivar Account">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-remove" ></span>
							</a>
						</td>
					</tr>
					
					<%}else{ %>
					<tr class="success">
						<td><%=personas.get(i).getApellido() %> <%=personas.get(i).getNombre() %></td>
						<td style="text-align:center;"><%=personas.get(i).getCorreo() %></td>
						<td style="text-align:center;"><%=personas.get(i).getTelefono() %></td>
						<td style="text-align:center;">
							<a href="" data-target="#mod3" data-toggle="modal" data-placement="right" title="Modificar Account" data-id="<%=personas.get(i).getId() %>" data-name="<%=personas.get(i).getNombre() %>" data-lastname="<%=personas.get(i).getApellido() %>" data-mail="<%=personas.get(i).getCorreo() %>" data-phone="<%=personas.get(i).getTelefono()%>" data-per="<%=personas.get(i).getPerfilUsuario()%>">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-edit" ></span>
							</a>
						</td>
						<td style="text-align:center;">
							<a href="" data-target="#mod" data-toggle="modal" data-id="<%=personas.get(i).getId()%>" data-placement="right" title="Desactivar Account">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-remove" ></span>
							</a>
						</td>
					</tr>
					<%} %>
					<%}else{
						if(persona.getId()==personas.get(i).getId()){%>
					<tr class="success">
						<td><%=personas.get(i).getApellido() %> <%=personas.get(i).getNombre() %></td>
						<td style="text-align:center;"><%=personas.get(i).getCorreo() %></td>
						<td style="text-align:center;"><%=personas.get(i).getTelefono() %></td>
						<td style="text-align:center;">
							<a href="" data-target="#mod3" data-toggle="modal" data-placement="right" title="Modificar Account" data-id="<%=personas.get(i).getId() %>" data-name="<%=personas.get(i).getNombre() %>" data-lastname="<%=personas.get(i).getApellido() %>" data-mail="<%=personas.get(i).getCorreo() %>" data-phone="<%=personas.get(i).getTelefono()%>" data-per="<%=personas.get(i).getPerfilUsuario()%>">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-edit" ></span>
							</a>
						</td>
						<td style="text-align:center;">
							<a href="" data-target="#mod"  class="not-active" data-toggle="modal" data-id="<%=personas.get(i).getId()%>" data-placement="right" title="Desactivar Account">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-remove" ></span>
							</a>
						</td>
					</tr>
					
					<%}else{ %>
					<tr class="active">
						<td><%=personas.get(i).getApellido() %> <%=personas.get(i).getNombre() %></td>
						<td style="text-align:center;"><%=personas.get(i).getCorreo() %></td>
						<td style="text-align:center;"><%=personas.get(i).getTelefono() %></td>
						<td style="text-align:center;">
							<a href="" data-target="#mod3" data-toggle="modal" data-placement="right" title="Modificar Account" data-id="<%=personas.get(i).getId() %>" data-name="<%=personas.get(i).getNombre() %>" data-lastname="<%=personas.get(i).getApellido() %>" data-mail="<%=personas.get(i).getCorreo() %>" data-phone="<%=personas.get(i).getTelefono()%>" data-per="<%=personas.get(i).getPerfilUsuario()%>">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon-edit" ></span>
							</a>
						</td>
						<td style="text-align:center;">
							<a href=""  data-target="#mod1" data-toggle="modal" data-id="<%=personas.get(i).getId()%>"  data-placement="right" title="Activar Account">
								<span style="margin-top:0.5px;" class="glyphicon glyphicon- glyphicon glyphicon-ok-circle" ></span>
							</a>
						</td>
					</tr>
					<%} %>		
					<%}} %>
				</tbody>	
			</table>	
		</div>	
	</div>
	<jsp:include page="includes/footer.jsp"></jsp:include>
	<jsp:include page="includes/modalsUsu.jsp"></jsp:include>
	<jsp:include page="includes/modalMensaje.jsp"></jsp:include>
</body>
</html>
