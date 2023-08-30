package DAOs;

import java.sql.Connection;
import java.util.ArrayList;

import entity.FacturaProducto;
import entity.Producto;

public class MySqlProductoDAO implements ProductoDAO{

	private Connection connection;

	public MySqlProductoDAO(Connection c) {
		this.connection = c;
	}

	@Override
	public ArrayList<Producto> getProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaProducto getFacturaProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProducto(Producto p) {
		// TODO Auto-generated method stub
		
	}

}
