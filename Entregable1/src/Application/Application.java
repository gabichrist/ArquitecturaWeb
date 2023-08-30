package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entity.Cliente;
import entity.Producto;
import service.ServicioCSVParser;
import service.ServicioCliente;
import service.ServicioProducto;

public class Application {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		ServicioCSVParser csvParser = new ServicioCSVParser();
		ServicioCliente svcCliente = new ServicioCliente();
		ServicioProducto svcProducto = new ServicioProducto();

		csvParser.loadData();
		Producto p = svcProducto.getProductoMasRecaudado();
		System.out.println("Producto más recaudado: " + p);
		List<Cliente> clientes = svcCliente.getClientesOrdenadosPorFacturacion();
		clientes.forEach(cliente -> {
			System.out.println("Cliente " + cliente);	
		});
	}

}
