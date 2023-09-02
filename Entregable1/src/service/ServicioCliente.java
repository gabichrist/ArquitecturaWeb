package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.ClienteFacturado;

public class ServicioCliente extends Servicio {

	public ServicioCliente() throws SQLException {
		super();
	}

	// Retorna una lista de clientes en orden descendente segun la facturacion
	public List<ClienteFacturado> getClientesOrdenadosPorFacturacion() throws SQLException {
		List<ClienteFacturado> clientes = new ArrayList<>();
		clientes = daoClient.getClientesPorFacturacion();	
		
		return clientes;
	}

}
