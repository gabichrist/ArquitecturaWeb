package DTOs;

public class Factura {
	int idFactura;
	Cliente c;

	public Factura(int idFactura, Cliente c) {
		super();
		this.idFactura = idFactura;
		this.c = c;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Cliente getCliente() {
		return c;
	}

	public void setCliente(Cliente c) {
		this.c = c;
	}

}
