package dao;

import java.sql.SQLException;

import entity.Producto;
import entity.ProductoConRecaudacion;

public interface DaoProducto extends Dao<Producto> {
	ProductoConRecaudacion getProductoMasRecaudado() throws SQLException;
}
