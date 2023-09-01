package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import entity.Producto;
import dao.Dao;
import dao.MySqlProductoDAO;


public class ServicioProducto extends Servicio {
	Dao<Producto> daoProducto = new MySqlProductoDAO();

	public ServicioProducto() throws SQLException {
		super();
	}

	// Retorna el producto con mas recaudacion.
	public Producto getProductoMasRecaudado() throws SQLException {	
		Producto productoMasRecaudado = this.daoProducto.getProductoMasRecaudado();
		return productoMasRecaudado;
	}
}





