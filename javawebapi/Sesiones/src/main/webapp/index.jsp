<%@page import="com.educacionit.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mi primera Sesion</title>
</head>
<body>
	<%
	String mensaje = (String) session.getAttribute("mensaje");
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	String id = (String) session.getAttribute("id");

	if (usuario == null) {
		response.sendRedirect("formulario.jsp");
	} else {
	%>

	<h1><%=mensaje%></h1>
	<h2><%=usuario.getCorreo()%></h2>
	<h3><%="Numero de Sesion: " + id%></h3>
	<%
	}
	%>
	
	<a  href="login?sesion=true">Cerrar Sesion</a>

</body>
</html>