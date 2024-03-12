<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int precioTotal = (int)request.getAttribute("precioTotal"); %>
<h4>El precio total de su pedido es: <%=precioTotal %></h4>
</body>
</html>