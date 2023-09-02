package factory;

import java.sql.SQLException;

import dao.DaoCliente;
import dao.DaoProducto;
import dao.Dao;
import entity.Factura;
import entity.FacturaProducto;

public interface DAOFactory {
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;

	public abstract DaoCliente getClienteDAO() throws SQLException;
	public abstract Dao<Factura> getFacturaDAO() throws SQLException;
	public abstract DaoProducto getProductoDAO() throws SQLException;
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
	
	public abstract void closeConnection() throws SQLException;
}
