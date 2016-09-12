<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="es">
<head>
<%EmpleadoBean usuario=(EmpleadoBean) session.getAttribute("sesion");%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0"> 

<!-- ******************************* PARA MODAL ******************************* -->
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<!-- *******************************PARA MODAL  ******************************* -->
<style>    
    td.alt { background-color: #ffc; background-color: rgba(255, 255, 0, 0.2); }
</style>
 <title>Consultar Asistencias</title>
   <% ArrayList<EmpleadoBean> empleados=(ArrayList) request.getAttribute("empleados");
   %>
   
<jsp:include page="menu.jsp"><jsp:param name="m" value="3" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">

<%if(usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
<jsp:include page="error.jsp"></jsp:include>
<%}else{ %>
<div class="container">

<p align="center" style="font-size: 30px;"> Consulta de Asistencias</p>
<br>
<br>
<table style="border-width: 1px;border-style: solid; padding: 5%;" class="table" >
<thead style="display:block; background-color: #36393b; color: #ffffff">
<tr style="border-width: 1px;border-style: solid;">
<th style="width: 180px;">DNI</th><th style="width: 260px;">Nombre</th><th style="width: 260px;">Apellido</th><th style="width: 180px;">Perfil</th><th style="width: 120px;">Estado</th><th style="width: 120px;">DETALLE</th></tr>
</thead>
<tbody  style="height: 300px;  overflow-y: auto; overflow-x: hidden; display:block;">
<%for(int i=0;i<empleados.size();i++){%>
<form action="ConsultarAsistencias" method="post">
<tr style="border-width: 1px;border-style: solid;" height="50px;">
<td style="width: 180px;"><%=empleados.get(i).getDni() %></td>
<td style="width: 260px;"><%=empleados.get(i).getNombre() %></td>
<td style="width: 260px;"><%=empleados.get(i).getApellido() %></<td>
<td style="width: 180px;"><%=empleados.get(i).getPerfilD() %></td><td style="width: 120px;">Activo</td>
<td style="width: 120px;"><input type="submit" value="Visualizar"></td>
</tr><input type="hidden" name="dni" value="<%=empleados.get(i).getDni() %>">
<input type="hidden" name="nombreC" value="<%=empleados.get(i).getNombre()%>&nbsp<%=empleados.get(i).getApellido() %>"></form><%} %>
</tbody>
 </table>
</div>   




	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/jquery.filtertable.min.js"></script>
    <script>
    $(document).ready(function() {
        $('table').filterTable({ // apply filterTable to all tables on this page
            quickList: [] // add some shortcut searches
        });
    });
    </script>
<%} %>    
</body>
</html>