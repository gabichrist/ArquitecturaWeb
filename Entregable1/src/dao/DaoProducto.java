package dao;

import java.sql.SQLException;

import entity.Producto;

public interface DaoProducto extends Dao<Producto> {
	Producto getProductoMasRecaudado() throws SQLException;
}
