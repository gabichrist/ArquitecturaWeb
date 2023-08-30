package DAOs;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;

//TODO: implements dao<Producto>
public interface ClienteDAO {
	
	public abstract ArrayList<Cliente> getClientes() throws SQLException;

	public abstract void addCliente(Cliente c) throws SQLException;

}
