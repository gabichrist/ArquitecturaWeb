package DAOs;

import java.util.ArrayList;

import entity.FacturaProducto;
import entity.Producto;

public interface ProductoDAO {
	public abstract ArrayList<Producto> getProductos();

	public abstract FacturaProducto getFacturaProductos();
	
	public abstract void addProducto(Producto p);
}
