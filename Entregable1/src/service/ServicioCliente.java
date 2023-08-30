package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;

public class ServicioCliente extends Servicio {

	public ServicioCliente() throws SQLException {
		super();
	}

	// TODO: Ejercicio 4
	public List<Cliente> getClientesOrdenadosPorFacturacion() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		return clientes;
	}

}
