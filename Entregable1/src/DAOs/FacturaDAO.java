package DAOs;

import java.util.ArrayList;

import entity.Cliente;
import entity.Factura;

public interface FacturaDAO {
	public abstract ArrayList<Factura> getFacturas();

	public abstract ArrayList<Cliente> getClientesOrderByFacturas();
	
	public abstract void addFactura(Factura f);
}
