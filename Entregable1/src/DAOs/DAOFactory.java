package DAOs;

public interface DAOFactory {
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;

	public abstract ClienteDAO getClienteDAO();
	public abstract FacturaDAO getFacturaDAO();
	public abstract ProductoDAO getProductoDAO();

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
