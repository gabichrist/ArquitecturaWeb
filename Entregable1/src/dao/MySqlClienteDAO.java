package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Cliente;
import factory.MySqlDAOFactory;

public class MySqlClienteDAO implements DaoCliente {
	private Connection connection;

	public MySqlClienteDAO() throws SQLException {
		this.openConnection();
		this.createTable();
	}

	@Override
	public List<Cliente> getAll() throws SQLException {
		this.connection = MySqlDAOFactory.getConnection();
		connection.beginRequest();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String select = "SELECT * FROM Cliente";
		PreparedStatement ps = connection.prepareStatement(select);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int idCliente = rs.getInt(1);
			String nombre = rs.getString(2);
			String email = rs.getString(3);
			Cliente c = new Cliente(idCliente, nombre, email);
			clientes.add(c);
		}
		rs.close();
		ps.close();
		return clientes;

	}

	@Override
	public void createTable() throws SQLException {
		String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente(" + "idCliente INT," + "nombre VARCHAR(500),"
				+ "email VARCHAR(150)," + "PRIMARY KEY(idCliente))";
		this.connection.prepareStatement(tablaCliente).execute();
		this.connection.commit();
	}

	@Override
	public void save(Cliente c) throws SQLException {
		this.openConnection();
		String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES(?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1, c.getIdCliente());
		ps.setString(2, c.getNombre());
		ps.setString(3, c.getEmail());
		ps.executeUpdate();
		ps.close();
		connection.commit();
	}

	@Override
	public void openConnection() {
		try {
			if (connection == null || connection.isClosed())
				connection = MySqlDAOFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadData(CSVParser csvContent) throws SQLException {
		for (CSVRecord row : csvContent) {
			int idCliente = Integer.parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");
			Cliente c = new Cliente(idCliente, nombre, email);
			this.save(c);
		}

	}

	@Override
	public ArrayList<Cliente> getClientesPorFacturacion() throws SQLException {
		connection = MySqlDAOFactory.getConnection();
		ArrayList<Cliente> clientes = new ArrayList<>();

		// Selecciona todos los clientes en orden descendente por su facturacion
		String select = "SELECT c.*, SUM(p.valor * fp.cantidad) as facturacion "
				+ "FROM cliente c JOIN factura f ON (c.idCliente = f.idCliente) "
				+ "JOIN facturaproducto fp ON (f.idFactura = fp.idFactura) "
				+ "JOIN producto p ON (p.idProducto = fp.idProducto) " + "WHERE c.idCliente = f.idCliente "
				+ "GROUP BY c.idCliente " + "ORDER BY `facturacion` DESC";

		PreparedStatement ps = connection.prepareStatement(select);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int idCliente = rs.getInt(1);
			String nombre = rs.getString(2);
			String email = rs.getString(3);
			Cliente cliente = new Cliente(idCliente, nombre, email);
			clientes.add(cliente);
		}

		connection.commit();
		connection.close();
		ps.close();
		rs.close();

		return clientes;
	}

}
