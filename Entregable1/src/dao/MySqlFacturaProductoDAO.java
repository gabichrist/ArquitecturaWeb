package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.FacturaProducto;
import factory.MySqlDAOFactory;

public class MySqlFacturaProductoDAO implements Dao<FacturaProducto> {
	private Connection connection;

	public MySqlFacturaProductoDAO() throws SQLException {
		this.openConnection();
		this.createTable();
	}

	@Override
	public List<FacturaProducto> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(FacturaProducto t) throws SQLException {
		String insert = "INSERT INTO FacturaProducto (idProducto, idFactura, cantidad) VALUES(?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1, t.getIdProducto());
		ps.setInt(2, t.getIdFactura());
		ps.setInt(3, t.getCantidad());
		ps.executeUpdate();
		ps.close();
		connection.commit();
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
	public void createTable() throws SQLException {
		String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS FacturaProducto(" + "idProducto INT,"
				+ "idFactura INT," + "FOREIGN KEY(idFactura) references Factura(idFactura),"
				+ "FOREIGN KEY(idProducto) references Producto(idProducto)," + "cantidad INT)";
		this.connection.prepareStatement(tablaFacturaProducto).execute();
		this.connection.commit();

	}

	@Override
	public void loadData(CSVParser csvContent) throws SQLException {
		for (CSVRecord row : csvContent) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idProducto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));
			FacturaProducto fp = new FacturaProducto(idFactura, idProducto, cantidad);
			this.save(fp);
		}
	}
}
