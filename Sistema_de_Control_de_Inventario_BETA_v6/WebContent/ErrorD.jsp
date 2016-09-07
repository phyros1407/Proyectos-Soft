<%@ page session="true"%>
<%
String errorC=request.getParameter("error3");
%>
<jsp:forward page="index.jsp">
	<jsp:param value="La cuenta esta descativada o no existe" name="error3"/>
</jsp:forward>
