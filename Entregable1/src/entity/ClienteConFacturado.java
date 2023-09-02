package entity;

public class ClienteConFacturado extends Cliente {
	float facturado;
	
	public ClienteConFacturado(int idCliente, String nombre, String email,  float facturado) {
		super(idCliente, nombre, email);
		this.facturado = facturado;
	}

	public float getVentaAcumulada() {
		return facturado;
	}

	@Override
	public String toString() {
		return super.toString() + ", Facturado: $" + facturado;
	}
	
	
}
