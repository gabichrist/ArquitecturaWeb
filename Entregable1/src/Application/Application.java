package Application;

import java.sql.SQLException;
import java.util.List;

import DAOs.ClienteDAO;
import DAOs.DAOFactory;
import entity.Cliente;

public class Application {

	public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		ClienteDAO daoClient = daoFactory.getClienteDAO();
		
		// código de prueba
		try {
			daoClient.addCliente(new Cliente(4, "segundo sombra", "segundo.gmx.net"));
		} catch (SQLException e) {
			// cliente test ya creado
		}

		List<Cliente> clientes;
		try {
			clientes = daoClient.getClientes();
			clientes.forEach(cliente -> System.out.println(cliente));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}

}
