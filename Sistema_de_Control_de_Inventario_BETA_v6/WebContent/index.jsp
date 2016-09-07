<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE HTML>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="css_no_bootstrap/login.css" media="screen" />

<title>Login</title>
</head>
<body>
	<div class="container" align="center" id="login">
		<header><img class="img-responsive" src="imagenes/logousmp.jpeg" alt="logo USMP" style="width: 285px; height: 272px; "></header>

		<form class="form-signin" method="post" action="UsuarioLogin" id="login2">
			<br/>
			<br/>
			<h3><font face="Impact">Iniciar Sesión</font></h3>
	 		<div class="form-group ">
   				<input id="texto1" type="text" name="usuario" placeholder="Usuario" class="form-control"  aria-describedby="sizing-addon1" value="" required>
	 		</div>
	 		<div class="form-group ">
				<input id="texto2" type="password" name="pass" placeholder="Password" class="form-control"  aria-describedby="sizing-addon1" value="" required><br/>
	 		</div>
			<button id="boton" type="submit" name="button"  class="btn btn-default">Iniciar Sesión</button>
			<br/>
			<input type="hidden" value="login" name="accion"/>
			<%
			String error=request.getParameter("error");
			if(error!=null){%>
				<br/>
				<div class="alert alert-warning form-group " style="width:300px;" >
  					<strong>Error!</strong> <%=error %> 
				</div>
			<%}%>
			<%
			String error2=request.getParameter("error2");
			if(error2!=null){%>
				<br/>
				<div class="alert alert-warning form-group " style="width:300px;" >
  					<strong>Error!</strong> <%=error2 %> 
				</div>
			<%}%>
			<%
			String error3=request.getParameter("error3");
			if(error3!=null){%>
				<br/>
				<div class="alert alert-warning form-group " style="width:300px;" >
  					<strong>Error!</strong> <%=error3 %> 
				</div>
			<%}%>
		</form>	
		<br>
		<p><h1><font face="Impact">Inventario de Fabrica de Software</font></h1>
	</div>
	<br/>
	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>