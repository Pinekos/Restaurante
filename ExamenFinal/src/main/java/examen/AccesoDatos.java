package examen;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatos {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurante?user=root";
	private static final String CSV_URL = "C:\\Users\\pilar\\eclipse-workspace\\ExamenFinal\\src\\main\\webapp\\pedidosCSV.csv";

	public static void exportarCSV(List<Pedido> pedidos) throws IOException {
		try (FileWriter fw = new FileWriter(CSV_URL, false)) {
			for (Pedido pe : pedidos) {
				String linea = pe.getFecha() + ", " + pe.getPrimero() + ", " + pe.getSegundo() + "," + pe.getPostre() + ", "
						+ pe.getObservacion();
				fw.write(linea);
			}
		}
	}
	
	public static void insertarPlato(Plato plato) {
		String sql = "INSERT INTO platos (nombre, precio) VALUES (?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setString(1, plato.getNombre());
				pstmt.setString(2, plato.getPrecio());

				pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarPedidos(int id) {
		String sql = "DELETE FROM pedidos WHERE id = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setInt(1, id);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static List<Pedido> recuperarPedidos() {
		List<Pedido> pedidos = new ArrayList<>();
		String sql = "SELECT mesa, primero, segundo, postre, observacion, fecha FROM pedidos";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						int mesa = rs.getInt("mesa");
						String primero = rs.getString("primero");
						String segundo = rs.getString("segundo");
						String postre = rs.getString("postre");
						String observacion = rs.getString("observacion");
						String fecha = rs.getString("fecha");
						pedidos.add(new Pedido(mesa, primero, segundo, postre, observacion, fecha));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public static void insertarPedido(Pedido pedido) {
		String sql = "INSERT INTO pedidos (mesa, primero, segundo, postre, observacion, fecha) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setInt(1, pedido.getMesa());
				pstmt.setString(2, pedido.getPrimero());
				pstmt.setString(3, pedido.getSegundo());
				pstmt.setString(4, pedido.getPostre());
				pstmt.setString(5, pedido.getObservacion());
				pstmt.setString(6, pedido.getFecha());

				pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Postre> recuperarPostres() {
		List<Postre> postres = new ArrayList<>();
		String sql = "SELECT nombre, precio FROM postres";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String precio = rs.getString("precio");
						postres.add(new Postre(nombre, precio));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postres;
	}
	
	public static List<Plato> recuperarPlatos() {
		List<Plato> platos = new ArrayList<>();
		String sql = "SELECT nombre, precio FROM platos";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						String precio = rs.getString("precio");
						platos.add(new Plato(nombre, precio));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return platos;
	}
	
	public static boolean validarSesion(String nombre, String pass) {
		String sql = "SELECT * FROM cocineros WHERE nombre = ? AND pass = ?";
		boolean userExists = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(DB_URL);
					PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setString(1, nombre);
				pstmt.setString(2, pass);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						userExists = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userExists;
	}
}
