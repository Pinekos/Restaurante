<%@ page import="examen.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedidos</title>
</head>
<body>
<% String nombreUsuario = (String) session.getAttribute("nombreUsuario"); %>
<% if (nombreUsuario == null) { %>
<% request.getRequestDispatcher("/insertarpedido.jsp").forward(request, response); %>
<% } %>
<% List<Pedido> pedidos = AccesoDatos.recuperarPedidos(); %>
<table>
        <tr>
            <th>Nº mesa</th>
            <th>Primero</th>
            <th>Segundo</th>
            <th>Postre</th>
            <th>Observacion</th>
            <th>Fecha</th>
        </tr>
        <% int contador = 0; %>
        <% for (Pedido pedido : pedidos) { %>
        <% contador+=1; %>
               <tr>
                   <td><%= pedido.getMesa() %></td>
                   <td><%= pedido.getPrimero() %></td>
                   <td><%= pedido.getSegundo() %></td>
                   <td><%= pedido.getPostre() %></td>
                   <td><%= pedido.getObservacion() %></td>
                   <td><%= pedido.getFecha() %></td>
                   <td>
                       <form action="ExamenServlet?action=eliminarPedido" method="post">
                           <input type="hidden" id ="pedidoEliminar" name="pedidoEliminar" value="<%=contador%>">
                           <button type="submit">Eliminar</button>
                       </form>
                   </td>
               </tr>
        <% } %>
    </table>
    <br><br>
    <form action="ExamenServlet?action=exportarCSV" method="post">
    <input type="submit" value="Exportar CSV">
    </form>
    <br>
	<a href="./insertarplatos.jsp">Ir a página para insertar nuevos platos</a>
</body>
</html>