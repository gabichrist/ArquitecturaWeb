package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;


public class ServicioCSVParser extends Servicio {

	public ServicioCSVParser() throws SQLException {
		super();
	}

	public void loadData() throws FileNotFoundException, IOException, SQLException {
		this.loadClientesCSV();
		this.loadProductosCSV();
		this.loadFacturasCSV();
		this.loadFacturaProductosCSV();
	}

	@SuppressWarnings("deprecation")
	private void loadClientesCSV() throws FileNotFoundException, IOException, SQLException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/resources/clientes.csv"));
		daoClient.loadData(parser);
	}

	@SuppressWarnings("deprecation")
	private void loadFacturasCSV() throws FileNotFoundException, IOException, SQLException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/resources/facturas.csv"));
		daoFactura.loadData(parser);
	}

	@SuppressWarnings("deprecation")
	private void loadProductosCSV() throws FileNotFoundException, IOException, SQLException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/resources/productos.csv"));
		daoProducto.loadData(parser);
	}

	@SuppressWarnings("deprecation")
	private void loadFacturaProductosCSV() throws FileNotFoundException, IOException, SQLException {
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/resources/facturas-productos.csv"));
		daoFacturaProducto.loadData(parser);
	}

}
