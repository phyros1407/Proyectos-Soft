<%@page import="beans.EmpleadoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<%EmpleadoBean usuario=(EmpleadoBean) session.getAttribute("sesion");
if(usuario!=null){%>
<head>
<meta charset="utf-8" http-equiv="Content-Type"  content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="./js/validaciones.js" charset="UTF-8"></script>
<script type="text/javascript" src="./js/ajax_call.js" charset="UTF-8"></script>
<title>Inicio</title>
<link rel="shortcut icon" href="images/icono.ico" />
</head>
<body style="background-color: #f7f7f7;">
	<jsp:include page="menu.jsp"><jsp:param name="m" value="1" /></jsp:include>
	
	
	<div>
	<h2 align="center">
	Bienvenido, seleccione una opción del menú para continuar.
	</h2>
	<p align="center">
	<img src="images/inicio.jpg"></p>
	</div>
	
</body>
<%}else{ %>
<a href="login.jsp">Iniciar sesión</a>
<%} %>
</html>