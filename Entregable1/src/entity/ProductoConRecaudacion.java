package entity;

public class ProductoConRecaudacion extends Producto {
	private float totalRecaudado;
	
	public ProductoConRecaudacion(int idProducto, String nombre, float valor, float totalRecaudado) {
		super(idProducto, nombre, valor);
		this.totalRecaudado = totalRecaudado;
	}

	@Override
	public String toString() {
		return super.toString() + ", Total Recaudado: $" + this.totalRecaudado;
	}

	public float getTotalRecaudado() {
		return totalRecaudado;
	}

}
