<%@page import="beans.ParametrosBean"%>
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
<% ArrayList<ParametrosBean> parametros=(ArrayList) request.getAttribute("parametros");%>

<script language="javascript" type="text/javascript">
function modificar(){
	document.getElementById('btnCancelar').setAttribute('style', 'visibility:visible');
	document.getElementById('btnGrabar').setAttribute('style', 'visibility:visible');
	document.getElementById('btnModificar').disabled=true;
	document.getElementById('seguro1').disabled=false;
	document.getElementById('seguro2').disabled=false;
	document.getElementById('seguro3').disabled=false;
}
function cancelar(){
	document.getElementById('btnCancelar').setAttribute('style', 'visibility:hidden');
	document.getElementById('btnGrabar').setAttribute('style', 'visibility:hidden');
	document.getElementById('btnModificar').disabled=false;
	document.getElementById('seguro1').disabled=true;
	document.getElementById('seguro2').disabled=true;
	document.getElementById('seguro3').disabled=true;
	document.getElementById('seguro1').value="<%=parametros.get(0).getPorcentaje()%>"
	document.getElementById('seguro2').value="<%=parametros.get(1).getPorcentaje()%>"
	document.getElementById('seguro3').value="<%=parametros.get(2).getPorcentaje()%>"	
}
</script> 

 
 <title>Actualizar Parámetros</title>   
   
<jsp:include page="menu.jsp"><jsp:param name="m" value="2" /></jsp:include>
</head>
<body style="background-color: #f7f7f7;">
<% if(parametros!=null){%>
<div class="center">

<form action="Parametros" method="post">
<%=parametros.get(0).getNombre() %> : &nbsp &nbsp<input type="number" id="seguro<%=parametros.get(0).getId_seg()%>" style="width: 60px;" max="99,99" min="1,00" name="seguro1" value="<%=parametros.get(0).getPorcentaje()%>" size="3" disabled>%
<br><br><%=parametros.get(1).getNombre() %> : &nbsp &nbsp<input type="number" id="seguro<%=parametros.get(1).getId_seg()%>" style="width: 60px;" max="99,99" min="1,00"  name="seguro2" value="<%=parametros.get(1).getPorcentaje()%>" size="3" disabled>%
<br><br><%=parametros.get(2).getNombre() %> : &nbsp &nbsp<input type="number" id="seguro<%=parametros.get(2).getId_seg()%>" style="width: 60px;" max="99,99" min="1,00" name="seguro3" value="<%=parametros.get(2).getPorcentaje()%>" size="3" disabled>%
<br><br><input type="button" value="Cancelar" id="btnCancelar" onclick="cancelar()" style="visibility: hidden;">&emsp;
<input type="submit" value="Grabar" id="btnGrabar" style="visibility: hidden;"> 
<br><br>
<p align="center"><input type="button" value="Modificar" id="btnModificar" onclick="modificar()" ></p>
</form>
<%} %>
</div>

</body>
</html>