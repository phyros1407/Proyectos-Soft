<%@ page session="true"%>
<%
String errorC=request.getParameter("error2");
%>
<jsp:forward page="index.jsp">
	<jsp:param value="Usuario y/o Contrase�a Errados" name="error2"/>
</jsp:forward>
