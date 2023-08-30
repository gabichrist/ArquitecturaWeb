package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;

public class MySqlClienteDAO implements ClienteDAO {
	// TODO: este private se puede mandar como protected a la interfaz/clase padre
	private Connection connection;

	@Override
	public ArrayList<Cliente> getClientes() throws SQLException {
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
		this.connection.close();
		
		// TODO: Ver cómo reabrir una conexión que fue cerrada
		// connection.close();
		return clientes;
	}

	@Override
	public void addCliente(Cliente c) throws SQLException {
		this.openConnection();
		String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES(?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1, c.getIdCliente());
		ps.setString(2, c.getNombre());
		ps.setString(3, c.getEmail());
		ps.executeUpdate();
		ps.close();
		connection.commit();
		connection.close();
	}
	
	
	// TODO: esto puede ir una única vez en la interfaz padre dao<T> que falta hacer
	private void openConnection() {
		try {
			if (connection == null || connection.isClosed())
				connection = MySqlDAOFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
