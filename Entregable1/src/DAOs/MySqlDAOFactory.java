package DAOs;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAOFactory {

	private ClienteDAO clienteDAO;
	private FacturaDAO facturaDAO;
	private ProductoDAO productoDAO;
	String driver = "com.mysql.cj.jdbc.Driver";
	String uri = "jdbc:mysql://localhost:3306/mysql";
	private Connection conn;

	public MySqlDAOFactory() {
		this.connect();
	}

	public MySqlDAOFactory(ClienteDAO clienteDAO, FacturaDAO facturaDAO, ProductoDAO productoDAO) {
		super();
		this.clienteDAO = clienteDAO;
		this.facturaDAO = facturaDAO;
		this.productoDAO = productoDAO;
	}

	private void connect() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			this.conn = DriverManager.getConnection(uri, "root", "password");
			conn.setAutoCommit(false);
			createTables();
			conn.close();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		} 
	}

	private void createTables() throws SQLException {
		String tablaCliente = "CREATE TABLE Cliente(" + "idCliente INT," + "nombre VARCHAR(500),"
				+ "email VARCHAR(150)," + "PRIMARY KEY(idCliente))";
		conn.prepareStatement(tablaCliente).execute();
		String tablaFactura = "CREATE TABLE Factura(" + "idFactura INT," + "idCliente INT," + "FOREIGN KEY(idCliente) references Cliente(idCliente),"
				+ "PRIMARY KEY(idFactura)) ";
		conn.prepareStatement(tablaFactura).execute();
		String tablaProducto = "CREATE TABLE Producto(" + "idProducto INT," + "nombre VARCHAR(45)," + "valor FLOAT,"
				+ "PRIMARY KEY(idProducto))";
		conn.prepareStatement(tablaProducto).execute();
		String tablaFacturaProducto = "CREATE TABLE FacturaProducto(" + "idProducto INT," + "idFactura INT," + "FOREIGN KEY(idFactura) references Factura(idFactura),"
				+ "FOREIGN KEY(idProducto) references Producto(idProducto)," + "cantidad INT)";
		conn.prepareStatement(tablaFacturaProducto).execute();
		
		conn.commit();

	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public FacturaDAO getFacturaDAO() {
		return facturaDAO;
	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

}
