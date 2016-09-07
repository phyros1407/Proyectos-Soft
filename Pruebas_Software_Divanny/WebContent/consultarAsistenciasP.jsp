<%@page import="beans.AsistenciasBean"%>
<%@page import="beans.EmpleadoBean"%>
<%@page import="java.util.ArrayList"%>
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

 <title>Consultar Asistencias Personales</title>
   <% ArrayList<AsistenciasBean> asistencias=(ArrayList) request.getAttribute("asistencia");
   String nombreC=(String) request.getAttribute("nombreC");%>
   
<jsp:include page="menu.jsp"><jsp:param name="m" value="3" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">
<% if(asistencias!=null){%>
<div class="container">

<p align="center" style="font-size: 30px;">Detalle Asistencias de <%=nombreC %></p>
<br>

<br>
<table style="border-width: 1px;border-style: solid; padding: 5%;" class="table">
<thead style="display:block; background-color: #36393b; color: #ffffff">
<tr style="border-width: 1px;border-style: solid;">
<th style="width: 180px;">Fecha</th><th style="width: 220px;">Hora Entrada</th><th style="width: 220px;">Hora refrigerio</th><th style="width: 220px;">Hora Regreso Ref</th><th style="width: 220px;">Hora Salida</th><th style="width: 120px;">Estado</th></tr>
</thead>
<tbody  style="height: 200px;  overflow-y: auto; overflow-x: hidden; display:block;">
<%for(int i=0;i<asistencias.size();i++){%>
<form action="Asistencias" method="post">      
<tr style="border-width: 1px;border-style: solid;" height="50px;">
<td style="width: 180px;"><%=asistencias.get(i).getFecha()%></td>
<td style="width: 220px;"><% if(asistencias.get(i).getHoraEnt()==null){%>No marcó<%}else{%><%=asistencias.get(i).getHoraEnt() %><%} %></td>
<td style="width: 220px;"><% if(asistencias.get(i).getHoraRef()==null){%>No marcó<%}else{%><%=asistencias.get(i).getHoraRef() %><%} %></<td>
<td style="width: 220px;"><% if(asistencias.get(i).getHoraSref()==null){%>No marcó<%}else{%><%=asistencias.get(i).getHoraSref() %><%} %></td>
<td style="width: 220px;"><% if(asistencias.get(i).getHoraSal()==null){%>No marcó<%}else{%><%=asistencias.get(i).getHoraSal() %><%} %></td>
<td style="width: 120px;"><%=asistencias.get(i).getEstadoD() %></td>
</tr></form>
<%} %>
</tbody>
 </table>
</div>   

<%}else{ %>
<p>NADA</p>
<%} %>
    
</body>
</html>