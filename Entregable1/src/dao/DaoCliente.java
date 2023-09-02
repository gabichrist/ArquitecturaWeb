package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;
import entity.ClienteFacturado;

public interface DaoCliente extends Dao<Cliente> {
	ArrayList<ClienteFacturado> getClientesPorFacturacion() throws SQLException;
}
