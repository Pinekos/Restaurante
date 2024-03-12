<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String nombreUsuario = (String) session.getAttribute("nombreUsuario"); %>
<% if (nombreUsuario == null) { %>
<% request.getRequestDispatcher("/insertarpedido.jsp").forward(request, response); %>
<% } %>
    <form action="ExamenServlet?action=insertarPlato" method="post">
	<label for="nuevoPlato">Nombre del plato: </label>
	<input type="text" id="nuevoPlato" name="nuevoPlato">
	<label for="nuevoPrecio">Precio: </label>
	<input type="text" id="nuevoPrecio" name="nuevoPrecio">
	<input type="submit" value="Añadir nuevo plato">
	</form>
</body>
</html>