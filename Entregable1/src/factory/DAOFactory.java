package factory;

import java.sql.SQLException;

import dao.Dao;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;

public interface DAOFactory {
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;

	public abstract Dao<Cliente> getClienteDAO() throws SQLException;
	public abstract Dao<Factura> getFacturaDAO() throws SQLException;
	public abstract Dao<Producto> getProductoDAO() throws SQLException;
	public abstract Dao<FacturaProducto> getFacturaProductoDAO() throws SQLException;

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return MySqlDAOFactory.getInstance();
		case DERBY_JDBC:
			return null;
		case JPA_HIBERNATE:
			return null;
		default:
			return null;
		}
	}
}
