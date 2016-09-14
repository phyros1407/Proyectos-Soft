<%@page import="beans.EmpleadoBean"%>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="images/icono.ico" />
<title>Insert title here</title>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<%int m=Integer.parseInt(request.getParameter("m"));
int w=1;int x=1;int y=1;int z=1; 
switch(m){ case 1: w=2;break;case 2: x=2;break;case 3: y=2;break;case 4: z=2;break;}
EmpleadoBean usuario=(EmpleadoBean) session.getAttribute("sesion");%>
</head>
<body>
<div class="container">
<div  class="col-xs-12 col-md-12 col-xs-offset-2" style="margin-top:30px;margin-bottom:30px;">
 <div class=" col-xs-6 col-md-6" >
  	<img class="img-responsive"  src="images/logoDiv.png">
  </div>
  <div class=" col-xs-6 col-md-6" >
  	<div style="margin-top:45px;margin-left:60px;">
	  	<h3><%=usuario.getPerfilD() %> :<br><%=usuario.getNombre()%> <%=usuario.getApellido() %></h3>
		<a href="Sistema"><span class="glyphicon glyphicon-off"></span>Cerrar Sesión</a>
  	</div>
  </div>
</div>

<div id="menunobu0" style="margin-left: 180px;">
        <ul> 
        
        <li><a href="#" class="menunobu<%=w%>">Asistencia</a>
        <%if(usuario.getPerfilD().equalsIgnoreCase("Secretaria")){ %>
        <ul> 
			<li><a href="Asistencias?est=0">Entrada</a></li>
			<li><a href="Asistencias?est=1">Inicio Refrigerio</a></li>
			<li><a href="Asistencias?est=2">Fin Refrigerio</a></li>
			<li><a href="Asistencias?est=3">Salida</a></li>
		</ul><%} %>
		</li>
                
        <li><a href="#" class="menunobu<%=x%>">Registrar</a>        
		<ul><%if(usuario.getPerfilD().equalsIgnoreCase("Administrador")){ %>
			<li><a href="Tipo_Empleado?tipo=5">Ventas</a></li> 
			<li><a href="Tipo_Empleado?tipo=4">Horas extras</a></li>		<%} %>
			<%if(usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
			<li><a href="Parametros">Parámetros</a></li><%} %>	
			<%if(usuario.getPerfilD().equalsIgnoreCase("Secretaria")){ %>        				
			<li><a href="justificacion.jsp">Justificación</a></li>	<%} %>	
		</ul>               
        </li>
        
        <li><a href="#" class="menunobu<%=y%>">Consultar</a>
        
        <ul> <%if(usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
        <li><a href="planillaMensual.jsp">Planillas Mensuales</a></li><%} %>
        <%if(usuario.getPerfilD().equalsIgnoreCase("Administrador")||usuario.getPerfilD().equalsIgnoreCase("Secretaria")){ %>
        <li><a href="ConsultarAsistencias">Asistencias</a></li><%} %>
        </ul>
        </li>
        
        <%if(usuario.getPerfilD().equalsIgnoreCase("Administrador")){ %>
        <li><a href="#" class="menunobu<%=z%>">Personal</a>
        <ul> 
        <li><a href="Empleado">Empleados</a></li> 
        </ul><%} %>
        
        <%if(usuario.getPerfilD().equalsIgnoreCase("Contador")){ %>
        <li><a href="#" class="menunobu<%=z%>">Generar</a>
        <ul> 
        <li><a href="PagosPlanilla">Planilla mensual</a></li> 
        </ul><%} %>
        </li>
        
        </ul>
</div>


</div>
<br>
<br>
<br>
</body>
</html>