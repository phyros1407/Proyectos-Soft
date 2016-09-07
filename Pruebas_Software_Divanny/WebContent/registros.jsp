<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- ******************************* PARA MODAL ******************************* -->
<script type="text/javascript" src="./js/jquery.js" charset="UTF-8"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<!-- *******************************PARA MODAL  ******************************* -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #fafafa;">
<div align="center">
<jsp:include page="menu.jsp"><jsp:param name="m" value="2" /></jsp:include>
</div>
<br><div class="center"><table style="border-width: 1px;border-style: solid; padding: 5%; width: 900px; height: 300px"><tr style="border-width: 1px;border-style: solid;"><td>DNI</td><td>USUARIO</td><td>CORREO</td><td>PERFIL</td><td>Estado</td><td colspan="2">OPERACIONES</td></tr>
<tr style="border-width: 1px;border-style: solid;"><td>71020745</td><td>G_Guili</td><td>ejemplo@hotmail.com</td><td>Administrador</td><td>Activo</td><td><a href="">Modificar</a></td><td><a href="">Desactivar</a></td></tr>
<tr style="border-width: 1px;border-style: solid;"><td>98765432</td><td>M_Botton</td><td>ejemplo1@hotmail.com</td><td>Contador</td><td>Activo</td><td><a href="">Modificar</a></td><td><a href="">Desactivar</a></td></tr>
<tr style="border-width: 1px;border-style: solid;"><td>12345678</td><td>M_Macla</td><td>ejemplo2@hotmail.com</td><td>Obrero</td><td>Activo</td><td><a href="">Modificar</a></td><td><a href="">Desactivar</a></td></tr>
<tr style="border-width: 1px;border-style: solid;"><td>13457867</td><td>V_liberty</td><td>ejemplo3@hotmail.com</td><td>Vendedor</td><td>Activo</td><td><a href="">Modificar</a></td><td><a href="">Desactivar</a></td></tr></table></div>

</body>
</html>