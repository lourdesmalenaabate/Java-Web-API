<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario</title>
</head>
<body>
	<h1>Inicio de Sesion</h1>

	<form action="login" method="get">
		<div>
			<label for="correo">Usuario:</label> <input type="email" id="correo"
				name="correo" placeholder="usuario@dominio.ext" required />
		</div>
		<div>
			<label for="clave">Clave:</label> <input type="password" id="clave"
				name="clave" placeholder="digite su contraseņa" required />
		</div>

		<div>
			<button type="submit">Enviar</button>
			<button type="reset">Limpiar</button>
		</div>

		<div>

			<%
			if (session.getAttribute("mensaje") != null) {
				response.sendRedirect("index.jsp");
			} else {

				String mensaje = (String) request.getAttribute("mensaje");

				if (mensaje != null) {
			%>

			<h2 style="color: red"><%=mensaje%></h2>

			<%
			}
			}
			%>


		</div>


	</form>

</body>
</html>