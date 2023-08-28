package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTOs.Cliente;

public class MySqlClienteDAO implements ClienteDAO{
	private Connection connection;
	public MySqlClienteDAO(Connection c) {
		this.connection = c;
	}
	
	@Override
	public ArrayList<Cliente> getClientes() throws SQLException {
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
		return clientes;
	}

	@Override
	public void addCliente(Cliente c) throws SQLException {
		String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES(?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1,c.getIdCliente());
		ps.setString(2, c.getNombre());
		ps.setString(3, c.getEmail());
		ps.executeUpdate();
		ps.close();
		connection.commit();
	}


}
