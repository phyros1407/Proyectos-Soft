<%@page import="beans.PlanillaBean"%>
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
   <% ArrayList<PlanillaBean> planillas=(ArrayList) request.getAttribute("pago");
   %>
   
<jsp:include page="menu.jsp"><jsp:param name="m" value="3" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">

<%if(!usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
<jsp:include page="error.jsp"></jsp:include>
<%}else{ %>
<div class="container">
<form action="PagosPlanilla" method="post">
<div class="col-md-3">
							<select name="selAno" id=""selAno"" class="form-control">
							<option value="2016">2016</option>
							</select>
</div>
<div class="col-md-3">
							<select name="selMes" id="selMes" class="form-control">
							<option value="1">Enero</option>
							<option value="2">Febrero</option>
							<option value="3">Marzo</option>
							<option value="4">Abril</option>
							<option value="5">Mayo</option>
							<option value="6">Junio</option>
							<option value="7">Julio</option>
							<option value="8">Agosto</option>
							<option value="9">Septiembre</option>
							</select>
</div>
<div class="col-md-3">
<input type="submit" class="btn btn-primary" value="Buscar">
</div>
</form>
<br>
<br>
<br><%if(planillas!=null){%>
<p align="center"><%=planillas.get(0).getMes() %>  del <%=planillas.get(0).getAno() %><%} %></p>
<br>
<br>
<table style="border-width: 1px;border-style: solid; padding: 5%;" class="table" >
<thead style="display:block; background-color: #36393b; color: #ffffff">
<tr style="border-width: 1px;border-style: solid;">
<th style="width: 200px;">DNI</th><th style="width: 180px;">Sueldo</th><th style="width: 180px;">Aumento</th><th style="width: 180px;">Descuento</th><th style="width: 120px;">Seguro Vida</th>
<th style="width: 120px;">Seguro Salud</th><th style="width: 120px;">Sueldo Neto</th></tr>
</thead>
<tbody  style="height: 300px;  overflow-y: auto; overflow-x: hidden; display:block;">
<%
if(planillas!=null){%>
<%for(int i=0;i<planillas.size();i++){%>
<form action="ConsultarAsistencias" method="post">
<tr style="border-width: 1px;border-style: solid;" height="50px;">
<td style="width: 200px;"><%=planillas.get(i).getDni() %></td>
<td style="width: 180px;"><%=planillas.get(i).getSueldo() %></td>
<td style="width: 180px;"><%=planillas.get(i).getAumento() %></<td>
<td style="width: 180px;"><%=planillas.get(i).getDescuento() %></td>
<td style="width: 120px;"><%=planillas.get(i).getSeguroVida() %></td>
<td style="width: 120px;"><%=planillas.get(i).getSeguroSalud() %></td>
<td style="width: 120px;"><%=planillas.get(i).getSueldoNeto()%></td>
</tr></form><%}} %>
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