package DAOs;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;

public interface ClienteDAO {
	
	public abstract ArrayList<Cliente> getClientes() throws SQLException;

	public abstract void addCliente(Cliente c) throws SQLException;

}
