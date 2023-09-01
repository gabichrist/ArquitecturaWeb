package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Cliente;

public class ServicioCliente extends Servicio {

	public ServicioCliente() throws SQLException {
		super();
	}

	// Retorna una lista de clientes en orden descendente segun la facturacion
	public List<Cliente> getClientesOrdenadosPorFacturacion() throws SQLException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = daoClient.getClientesPorFacturacion();	
		
		return clientes;
	}

}
