package service;

import java.sql.SQLException;

import dao.Dao;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import factory.DAOFactory;

public abstract class Servicio {
	protected Dao<Cliente> daoClient;
	protected Dao<Factura> daoFactura;
	protected Dao<Producto> daoProducto;
	protected Dao<FacturaProducto> daoFacturaProducto;
	
	public Servicio() throws SQLException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		daoClient = daoFactory.getClienteDAO();
		daoFactura = daoFactory.getFacturaDAO();
		daoProducto = daoFactory.getProductoDAO();
		daoFacturaProducto = daoFactory.getFacturaProductoDAO();
	}
}
