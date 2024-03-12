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
</body>
</html>