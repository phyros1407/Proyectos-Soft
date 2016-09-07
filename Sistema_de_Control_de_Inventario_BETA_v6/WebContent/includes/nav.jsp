<%@page import="beans.PersonaBean"%>
<%@page session="true" %>
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


<script type="text/javascript">
	function cargarUsu(codigo){
		
		var ph="buscar";
		
		$.post('Persona',{
			codigo : codigo,
			ph:ph
			
		},function(response){
			
			var codigo=response['object']['id'];
			var nomU=response['object']['nombre'];
			var apeU=response['object']['apellido'];
			var correo=response['object']['correo'];
			var tele=response['object']['telefono'];
			
			
			
			 var slastname=apeU.substr(0,apeU.indexOf(" "));
		     var mlastname=apeU.substr(apeU.indexOf(" "));
			
			
			$('#codU').val(codigo);
			$('#apellidoPat').val(slastname.trim());
			$('#apellidoMat').val(mlastname.trim());
			$('#nombreUsu').val(nomU);
			$('#correoUsu').val(correo);
			$('#telefonoUsu').val(tele);
			
		});
	}
</script>


<head>
</head>	
<div class="page-header" >
	<img id="imagen-titulo" class="img-responsive" alt="cabecera" src="imagenes/logo_adm.png" style="margin-top:30px;width: 1200px;">
</div>
<nav class="navbar navbar-default " id="navegacion">
	 <div class="container-fluid">
    	<div class="navbar-header">
    		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar" style="background-color:#8A0829;">
        		<span class=" glyphicon glyphicon-th-list" style="color:#FFFFFF;"></span>
      		</button>
    	</div>
    	<div class="collapse navbar-collapse" id="myNavbar">
    	<% if(persona.getPerfilUsuario()==1){%>
    			<ul class="nav navbar-nav" id="navegacion">
      				<li class="dropdown">
        			<a  id="salir" class="dropdown-toggle" data-toggle="dropdown"  role ="button" aria-haspopup="true" aria-expanded="false">
        			Inventario </a>
        				<ul class="dropdown-menu dropdown-content">
          					<li><a href="Articulo?ph=ifsLa"  id="botones">Articulos </a></li>
          					<li><a href="Articulo?ph=ifsLaa" id="botones">Articulos fuera de stock</a></li>
        				</ul>
        			</li>
        			<li class="dropdown">
        			<a id="salir" class="dropdown-toggle" data-toggle="dropdown"  role ="button" aria-haspopup="true" aria-expanded="false">
        			Movimientos </a>
        			<ul class="dropdown-menu dropdown-content">
        				<li><a href="Movimiento?ph=ifsLmm&&npp=<%=preS+persona.getId()+postS%>&&t=<%=Integer.toString(persona.getId()).length() %>" id="botones">Mis Movimientos</a></li>
        				<li><a href="Movimiento?ph=ifsLm" id="botones">Reporte de Movimientos</a></li>
        			</ul>	
        			</li>
					
					<li><a href="Persona"  id="salir" >Usuarios</a></li>
					
    			</ul>
    	<% }else if(persona.getPerfilUsuario()==2){%>
    			<ul class="nav navbar-nav" id="navegacion">
      				<li class="dropdown">
        			<a  id="salir" class="dropdown-toggle" data-toggle="dropdown"  role ="button" aria-haspopup="true" aria-expanded="true">
        			Inventario </a>
        				<ul class="dropdown-menu dropdown-content">
          					<li><a href="Articulo?ph=ifsLa"  id="botones">Articulos </a></li>
          					<li><a href="Articulo?ph=ifsLaa" id="botones">Articulos fuera de stock</a></li>
        				</ul>
        			</li>
        			<li class="dropdown">
        				<a  id="salir" class="dropdown-toggle" data-toggle="dropdown"  role ="button" aria-haspopup="true" aria-expanded="true">
        				Movimientos </a>
        					<ul class="dropdown-menu">
        						<li><a href="Movimiento?ph=ifsLmm&&npp=<%=preS+persona.getId()+postS%>&&t=<%=Integer.toString(persona.getId()).length() %>" id="botones">Mis Movimientos</a></li>
        						<li><a href="Movimiento?ph=ifsLm" id="botones">Reporte de Movimientos</a></li>
        					</ul>	
        			</li>
					
    			</ul>
    	
    	<% }else{%>
    			<ul class="nav navbar-nav" id="navegacion">
      				<li><a href="Articulo?ph=ifsLa"  id="botones">Articulos</a></li>
					<li><a href="Movimiento?ph=ifsLmm&&npp=<%=preS+persona.getId()+postS%>&&t=<%=Integer.toString(persona.getId()).length() %>" id="botones">Mis movimientos</a></li>
    			</ul>
    	
    	<%}%>
    		  <ul class="nav navbar-nav navbar-right">
    		  	<!-- <li><a href="" data-target="#modEmail" data-toggle="modal" class="glyphicon glyphicon-envelope"></a></li> -->
    		  	<li><a style="color:#8A0829;"><b><%=persona.getNombre() %> <%=persona.getApellido() %></b></a></li>
        		<li class="dropdown">
        			<a  id="salir" class="dropdown-toggle glyphicon glyphicon-off" data-toggle="dropdown" href="#">
        			</a>
        				<ul class="dropdown-menu dropdown-menu-right dropdown-content">
          					<li><a href="" data-target="#modConf" data-toggle="modal" onclick="cargarUsu(<%=persona.getId()%>);">Configuracion</a></li>
      						<li role="presentation" class="divider"></li>
      						<li><a href="Sistema">Cerrar Sesion</a></li>
        				</ul>
        		</li>
      		  </ul>
  		</div>
  	</div>      
</nav>

<jsp:include page="modalConfig.jsp"></jsp:include>
