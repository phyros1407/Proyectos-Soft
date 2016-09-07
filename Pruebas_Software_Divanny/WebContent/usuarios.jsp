<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<!-- ******************************* PARA MODAL ******************************* -->
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<!-- *******************************PARA MODAL  ******************************* -->
 <title>Empleados</title>
<script>$(document).ready(function(){
    $("#loader").click(function(){
                // Load the page into the div
        $("#resultreturn").load("detalle.jsp");
                // Show the modal dialog
        $("#resultreturn").dialog({ modal: true });
    });
});
</script>   
<jsp:include page="menu.jsp"><jsp:param name="m" value="4" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">

<div class="container" style="overflow:scroll; height: 450px; width: 800px;">
	<div class="CSSTableGenerator" >
	<table >
	<tr>
		<td>DNI</td>
		<td>Usuario</td>
		<td>Password</td>
		<td>Estado</td>
		<td>Opciones</td>
	</tr>
	<tr>
		<td><p align="center">77777777</p></td>
		<td><p align="center">Username</p></td>
		<td><p align="center">*********</p></td>
		<td><p align="center">A</p></td>
		<td><p align="center"><a data-toggle="modal" href="#myModal">Modificar&nbsp </a>|<a href="">Desactivar&nbsp</a></p></td>
		
	</tr>		
	</table></div></div>
<br>

</body>
<!-- Modal -->
<div class="modal fade" style="" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <jsp:include page="modal1.jsp"><jsp:param value="2" name="i"/> </jsp:include>
</div>
<!-- Js vinculados -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</html>