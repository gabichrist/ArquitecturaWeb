package service;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoCliente;
import dao.DaoProducto;
import entity.Factura;
import entity.FacturaProducto;
import factory.DAOFactory;

public abstract class Servicio {
	protected DaoCliente daoClient;
	protected Dao<Factura> daoFactura;
	protected DaoProducto daoProducto;
	protected Dao<FacturaProducto> daoFacturaProducto;
	
	public Servicio() throws SQLException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		daoClient = daoFactory.getClienteDAO();
		daoFactura = daoFactory.getFacturaDAO();
		daoProducto = daoFactory.getProductoDAO();
		daoFacturaProducto = daoFactory.getFacturaProductoDAO();
	}
}
