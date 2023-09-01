package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cliente;

public interface DaoCliente extends Dao<Cliente> {
	ArrayList<Cliente> getClientesPorFacturacion() throws SQLException;
}
