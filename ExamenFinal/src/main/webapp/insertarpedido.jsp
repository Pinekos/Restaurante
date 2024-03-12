<%@ page import="examen.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
<% List<Plato> platos = AccesoDatos.recuperarPlatos();%>
<% List<Postre> postres = AccesoDatos.recuperarPostres();%>
<% int precioPrimero = 0; %>
<% int precioSegundo = 0; %>
<% int precioPostre = 0; %>

<form action="ExamenServlet?action=login" method="post">
<label for="nombreUsuario">Nombre de usuario: </label>
<input type="text" id="nombreUsuario" name="nombreUsuario"><br>
<label for="contrasena">Contraseña: </label>
<input type="password" id="pass" name="pass">
<input type="submit">
</form>
<br><br>
<form action="ExamenServlet?action=pedirCuenta" method="post">

<label for="numeroMesa">Numero de mesa:</label>
<select id="numeroMesa" name="numeroMesa">
  <% for (int i = 1; i<9; i++) { %>
  <option value="<%= i%>"><%= i%></option>
  <% } %>
</select><br>

<label for="primero">Selecciona el primero:</label>
<select id="primero" name="primero">
  <% for (Plato plato : platos) { %>
  <option value="<%= plato.getNombre()%>"><%= plato.getNombre()%>(<%= plato.getPrecio()%>€)</option>
  <% } %>
</select><br>

<label for="segundo">Selecciona el segundo:</label>
<select id="segundo" name="segundo">
  <% for (Plato plato : platos) { %>
  <option value="<%= plato.getNombre()%>"><%= plato.getNombre()%>(<%= plato.getPrecio()%>€)</option>
  <% } %>
</select><br>

<label for="postre">Selecciona el postre:</label>
<select id="postre" name="postre">
  <% for (Postre postre : postres) { %>
  <option value="<%= postre.getNombre()%>"><%= postre.getNombre()%>(<%= postre.getPrecio()%>€)</option>
  <% } %>
</select><br><br>
<input type="hidden" id ="fechaActual" name="fechaActual" value="<%=LocalDate.now()%>">
<textarea id="observacion" name="observacion" placeholder="Espacio para observaciones"></textarea>
<br><br><br><br>
<input type="submit">
</form>
</body>
</html>
