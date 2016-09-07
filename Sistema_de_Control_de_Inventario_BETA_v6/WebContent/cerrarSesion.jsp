<%@ page session="true"%>
<%
HttpSession sesionNo=request.getSession();
sesionNo.removeAttribute("misesion");
%>
<jsp:forward page="index.jsp"></jsp:forward>
