package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Cliente;
import entity.ClienteConFacturado;

public class ServicioCliente extends Servicio {

	public ServicioCliente() throws SQLException {
		super();
	}

	// Retorna una lista de clientes en orden descendente segun la facturacion
	public List<ClienteConFacturado> getClientesOrdenadosPorFacturacion() throws SQLException {
		List<ClienteConFacturado> clientes = new ArrayList<>();
		clientes = daoClient.getClientesPorFacturacion();	
		
		return clientes;
	}

}
