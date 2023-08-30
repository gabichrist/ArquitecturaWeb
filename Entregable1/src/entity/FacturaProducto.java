package entity;

public class FacturaProducto {
	Factura f;
	Producto p;
	int cantidad;

	public FacturaProducto(Factura f, Producto p, int cantidad) {
		super();
		this.f = f;
		this.p = p;
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return f;
	}

	public void setFactura(Factura f) {
		this.f = f;
	}

	public Producto getProducto() {
		return p;
	}

	public void setProducto(Producto p) {
		this.p = p;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
