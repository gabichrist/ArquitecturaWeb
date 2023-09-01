package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Dao;
import entity.Cliente;
import factory.DAOFactory;

public class ServicioCliente extends Servicio {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

	public ServicioCliente() throws SQLException {
		super();
	}

	// Retorna una lista de clientes en orden descendente segun la facturacion
	public List<Cliente> getClientesOrdenadosPorFacturacion() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Dao<Cliente> daoCliente = daoFactory.getClienteDAO();
		clientes = daoCliente.getClientesPorFacturacion();	
		
		return clientes;
	}

}
