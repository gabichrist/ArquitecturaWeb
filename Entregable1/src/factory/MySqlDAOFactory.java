package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.Dao;
import dao.DaoCliente;
import dao.DaoProducto;
import dao.MySqlClienteDAO;
import dao.MySqlFacturaDAO;
import dao.MySqlFacturaProductoDAO;
import dao.MySqlProductoDAO;
import entity.Factura;
import entity.FacturaProducto;

public class MySqlDAOFactory implements DAOFactory {

	private DaoCliente clienteDAO;
	private Dao<Factura> facturaDAO;
	private DaoProducto productoDAO;
	private Dao<FacturaProducto> facturaProductoDAO;

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
			Connection baseConn = DriverManager.getConnection(uri, dbUser, dbPassword);
			baseConn.prepareStatement("CREATE DATABASE IF NOT EXISTS " + dbName).execute();
			baseConn.close();

			this.conn = DriverManager.getConnection(uri + dbName, dbUser, dbPassword);
			this.conn.setAutoCommit(false);
		} catch (IllegalArgumentException | SecurityException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	protected static DAOFactory getInstance() {
		if (MySqlDAOFactory.instance == null) {
			MySqlDAOFactory.instance = new MySqlDAOFactory();
			MySqlDAOFactory.instance.connect();
		}

		return MySqlDAOFactory.instance;
	}

	public DaoCliente getClienteDAO() throws SQLException {
		if (this.clienteDAO == null)
			this.clienteDAO = new MySqlClienteDAO();

		return clienteDAO;
	}

	public Dao<Factura> getFacturaDAO() throws SQLException {
		if (this.facturaDAO == null)
			this.facturaDAO = new MySqlFacturaDAO();

		return facturaDAO;
	}

	@Override
	public Dao<FacturaProducto> getFacturaProductoDAO() throws SQLException {
		if (this.facturaProductoDAO == null)
			this.facturaProductoDAO = new MySqlFacturaProductoDAO();

		return facturaProductoDAO;
	}

	@Override
	public DaoProducto getProductoDAO() throws SQLException {
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
			e.printStackTrace();
		}
		return conn;
	}
}
