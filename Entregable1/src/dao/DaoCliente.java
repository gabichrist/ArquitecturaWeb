package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;
import entity.ClienteConFacturado;

public interface DaoCliente extends Dao<Cliente> {
	ArrayList<ClienteConFacturado> getClientesPorFacturacion() throws SQLException;
}
