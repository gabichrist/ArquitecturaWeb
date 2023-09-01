package service;

import java.sql.SQLException;

import entity.Producto;

public class ServicioProducto extends Servicio {

	public ServicioProducto() throws SQLException {
		super();
	}

	public Producto getProductoMasRecaudado() throws SQLException {
		return this.daoProducto.getProductoMasRecaudado();
	}
}
