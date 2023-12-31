package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Factura;
import factory.MySqlDAOFactory;

public class MySqlFacturaDAO implements Dao<Factura> {
	private Connection connection;

	public MySqlFacturaDAO() throws SQLException {
		this.openConnection();
		this.createTable();
	}

	@Override
	public List<Factura> getAll() {
		return null;
	}

	@Override
	public void save(Factura t) throws SQLException {
		String insert = "INSERT INTO Factura (idCliente, idFactura) VALUES(?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1, t.getIdCliente());
		ps.setInt(2, t.getIdFactura());
		ps.executeUpdate();
		ps.close();
		connection.commit();
	}

	@Override
	public void createTable() throws SQLException {
		String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura(" + "idFactura INT," + "idCliente INT,"
				+ "FOREIGN KEY(idCliente) references Cliente(idCliente)," + "PRIMARY KEY(idFactura)) ";
		this.connection.prepareStatement(tablaFactura).execute();
		this.connection.commit();
	}

	@Override
	public void openConnection() {
		try {
			if (connection == null || connection.isClosed())
				connection = MySqlDAOFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadData(CSVParser csvContent) throws SQLException {
		for (CSVRecord row : csvContent) {
			int idCliente = Integer.parseInt(row.get("idCliente"));
			int idFactura = Integer.parseInt(row.get("idFactura"));
			Factura f = new Factura(idFactura, idCliente);
			this.save(f);
		}
	}
}
