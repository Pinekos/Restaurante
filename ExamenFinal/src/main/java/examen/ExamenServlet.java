package examen;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ExamenServlet
 */
public class ExamenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		switch(action) {
		case "login":
			iniciarSesion(request, response);
			break;
			
		case "logout":
			cerrarSesion(request, response);
			break;
		
		case "pedirCuenta":
			insertarPedido(request, response);
			break;

		case "insertarPedido":
			insertarPedido(request, response);
			break;
			
		case "eliminarPedido":
			eliminarPedido(request, response);
			break;
			
		case "exportarCSV":
			exportarCSV(request, response);
			
		case "insertarPlato":
			insertarPlato(request, response);
			break;
		}
	}
	
	private void insertarPlato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nuevoPlato");
		String precio = request.getParameter("nuevoPrecio");
		Plato plato = new Plato(nombre, precio);
		AccesoDatos.insertarPlato(plato);
		request.getRequestDispatcher("/mostrarpedidos.jsp").forward(request, response);
	}

	private void exportarCSV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Pedido> pedidos = AccesoDatos.recuperarPedidos();
		AccesoDatos.exportarCSV(pedidos);
		request.getRequestDispatcher("/mostrarpedidos.jsp").forward(request, response);
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pedidoEliminar = Integer.parseInt(request.getParameter("pedidoEliminar"));
		AccesoDatos.eliminarPedidos(pedidoEliminar);
		request.getRequestDispatcher("/mostrarpedidos.jsp").forward(request, response);
	}

	private void insertarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String mesa = request.getParameter("numeroMesa");
	    int numeroMesa = Integer.parseInt(mesa);
	    String primero = request.getParameter("primero");
	    String segundo = request.getParameter("segundo");
	    String postre = request.getParameter("postre");
	    String observacion = request.getParameter("observacion");
	    String fecha = request.getParameter("fechaActual");
	    
	    Pedido pedido = new Pedido(numeroMesa, primero, segundo, postre, observacion, fecha);
	    
	    AccesoDatos.insertarPedido(pedido);
	    request.getRequestDispatcher("/insertarpedido.jsp").forward(request, response);
	    //pedirCuenta(request, response);
	}


	private void pedirCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int precioPrimero = Integer.parseInt(request.getParameter("primero"));
		int precioSegundo = Integer.parseInt(request.getParameter("segundo"));
		int precioPostre = Integer.parseInt(request.getParameter("postre"));
		int precioTotal = precioPrimero + precioSegundo + precioPostre;
		request.setAttribute("precioTotal", precioTotal);
		request.getRequestDispatcher("/factura.jsp").forward(request, response);
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.getRequestDispatcher("/insertarpedido.jsp").forward(request, response);
	}
	
	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = request.getParameter("nombreUsuario");
		String passUsuario = request.getParameter("pass");
		
		if(AccesoDatos.validarSesion(nombreUsuario, passUsuario)) {
		HttpSession session = request.getSession();
		session.setAttribute("nombreUsuario", nombreUsuario);
		request.getRequestDispatcher("/mostrarpedidos.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/insertarpedido.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
