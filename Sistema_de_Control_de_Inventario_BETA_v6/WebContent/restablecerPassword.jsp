<%@page import="beans.PersonaBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
 
 <link rel="stylesheet" type="text/css" href="css_no_bootstrap/login.css" media="screen" />

<script type="text/javascript">
    function validarPasswd(){
    
    	var p1 = document.getElementById("texto1").value;
    	var p2 = document.getElementById("texto2").value;
    
        
    	if (p1 != p2) {
      		document.getElementById("errorC1").innerHTML="Las contraseñas deben coincidir";
      		return false;
    	}
      	document.getElementById("errorC1").innerHTML="";
    	
    	return true;
    }
</script>

<%
	PersonaBean persona = null;
	HttpSession sesionOk=request.getSession();
	if(sesionOk.getAttribute("misesion")==null){%>
		<jsp:forward page="index.jsp">
			<jsp:param name="error" value="Debe iniciar Sesion"/>
		</jsp:forward>
	<%}else{
	
		persona=(PersonaBean)session.getAttribute("misesion");
		
	}

 %>
<title>restablecer contraseña</title>
</head>

<body>
<div class="container" align="center">

<header><img src="imagenes/buho.jpg" alt="logo USMP" style="width: 285px; height: 272px; "></header>

<form class="form-signin" method="post" action="RestablecerPassword" onSubmit="return validarPasswd()">
<br/>
<br/>
	 <div class="form-group">
	 	<input type="hidden" name="id" value="<%=persona.getId()%>">
	 	<label for="texto1">Nueva Password :</label>
   		<input id="texto1" type="password" name="pass1" placeholder="Nueva Password" class="form-control"  aria-describedby="sizing-addon1" pattern=".{8,13}" required  title="Ingrese contraseña de 8 a 13 caracteres">
		<b id="errorC1" style="color:red;"></b>
	 </div>
	 <div class="form-group">
		<label for="texto1">Confirmar Password :</label>
		<input id="texto2" type="password"  placeholder="Confirmar Password" class="form-control"  aria-describedby="sizing-addon1" pattern=".{8,13}" required  title="Ingrese contraseña de 8 a 13 caracteres"><br/>
	 </div>
		<button id="boton" type="submit" name="button"  class="btn btn-default">Restablecer password</button>
		<br/>
		
</form>
<br>
<p>
<h1><font face="Impact">Inventario de Fabrica de Software</font></h1>
</p>

</div>
<br/>
<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>