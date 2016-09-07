<%@page import="beans.PersonaBean"%>
<%@page session="true" %>
<%
	PersonaBean persona = null;
	HttpSession sesionOk=request.getSession();
	if(sesionOk.getAttribute("misesion")==null){%>
		<jsp:forward page="index.jsp">
			<jsp:param name="error" value="Debe iniciar Sesion"/>
		</jsp:forward>
	<%}else{
	
		persona=(PersonaBean)session.getAttribute("misesion");
		
	} %>

<%if(persona.getPerfilUsuario()==1||persona.getPerfilUsuario()==2){ %>
	
		<a href="agregarArt.jsp" class="btn btn-default  " style="background-color:#8A0829; color:white; margin-bottom:3px;"  data-target="#mod3" data-toggle="modal">
		<strong>Agregar Articulo</strong>
		</a>
	
<%} %>