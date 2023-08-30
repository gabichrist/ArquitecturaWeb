package DAOs;

import java.sql.Connection;
import java.util.ArrayList;

import entity.Cliente;
import entity.Factura;

public class MySqlFacturaDAO implements FacturaDAO{

	private Connection connection;

	public MySqlFacturaDAO(Connection c) {
		this.connection = c;
	}

	@Override
	public ArrayList<Factura> getFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Cliente> getClientesOrderByFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFactura(Factura f) {
		// TODO Auto-generated method stub
		
	}

}
