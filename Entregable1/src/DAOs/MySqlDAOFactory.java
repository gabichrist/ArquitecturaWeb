package DAOs;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAOFactory implements DAOFactory {

	private ClienteDAO clienteDAO;
	private FacturaDAO facturaDAO;
	private ProductoDAO productoDAO;
	// private final String driver = "com.mysql.cj.jdbc.Driver";
	private final static String uri = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "entregable1";
	private final static String dbUser = "root";
	private final static String dbPassword = "password";
	private Connection conn;
	static MySqlDAOFactory instance = null;

	private MySqlDAOFactory() {
		this.connect();
	}

	private void connect() {
		try {
			// TODO: Eliminaría esta línea, dado que parece no ser necesaria para el
			// DriverManager
			// Class.forName(driver).getDeclaredConstructor().newInstance();

			// Crear base de datos si no existe:
			Connection baseConn = DriverManager.getConnection(uri, dbUser, dbPassword);
			baseConn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + dbName).execute();
			baseConn.close();

			this.conn = DriverManager.getConnection(uri + dbName, dbUser, dbPassword);
			this.conn.setAutoCommit(false);

			// TODO: es responsabilidad de cada DAO crear sus tablas
			createTables();

			// TODO: Ver cómo reabrir una conexión que fue cerrada
			// this.conn.close();
		} catch (IllegalArgumentException | SecurityException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void createTables() throws SQLException {
		String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente(" + "idCliente INT," + "nombre VARCHAR(500),"
				+ "email VARCHAR(150)," + "PRIMARY KEY(idCliente))";
		conn.prepareStatement(tablaCliente).execute();
		String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura(" + "idFactura INT," + "idCliente INT,"
				+ "FOREIGN KEY(idCliente) references Cliente(idCliente)," + "PRIMARY KEY(idFactura)) ";
		conn.prepareStatement(tablaFactura).execute();
		String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto(" + "idProducto INT," + "nombre VARCHAR(45),"
				+ "valor FLOAT," + "PRIMARY KEY(idProducto))";
		conn.prepareStatement(tablaProducto).execute();
		String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS FacturaProducto(" + "idProducto INT,"
				+ "idFactura INT," + "FOREIGN KEY(idFactura) references Factura(idFactura),"
				+ "FOREIGN KEY(idProducto) references Producto(idProducto)," + "cantidad INT)";
		conn.prepareStatement(tablaFacturaProducto).execute();

		conn.commit();
	}

	protected static DAOFactory getInstance() {
		if (MySqlDAOFactory.instance == null) {
			MySqlDAOFactory.instance = new MySqlDAOFactory();
			MySqlDAOFactory.instance.connect();
		}

		return MySqlDAOFactory.instance;
	}

	public ClienteDAO getClienteDAO() {
		if (this.clienteDAO == null)
			this.clienteDAO = new MySqlClienteDAO();

		return clienteDAO;
	}

	public FacturaDAO getFacturaDAO() {
		if (this.facturaDAO == null)
			this.facturaDAO = new MySqlFacturaDAO();

		return facturaDAO;
	}

	public ProductoDAO getProductoDAO() {
		if (this.productoDAO == null)
			this.productoDAO = new MySqlProductoDAO();

		return productoDAO;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MySqlDAOFactory.uri + MySqlDAOFactory.dbName, MySqlDAOFactory.dbUser,
					MySqlDAOFactory.dbPassword);
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
