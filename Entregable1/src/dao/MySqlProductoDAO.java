package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Producto;
import factory.MySqlDAOFactory;

public class MySqlProductoDAO implements Dao<Producto> {

	private Connection connection;

	public MySqlProductoDAO() throws SQLException {
		this.openConnection();
		this.createTable();
	}

	@Override
	public Optional<Producto> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Producto t) throws SQLException {
		String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES(?,?,?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		ps.setInt(1, t.getIdProducto());
		ps.setString(2, t.getNombre());
		ps.setFloat(3, t.getValor());
		ps.executeUpdate();
		ps.close();
		connection.commit();
	}

	@Override
	public void update(Producto t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Producto t) {
		// TODO Auto-generated method stub

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
		String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto(" + "idProducto INT," + "nombre VARCHAR(45),"
				+ "valor FLOAT," + "PRIMARY KEY(idProducto))";
		connection.prepareStatement(tablaProducto).execute();
		this.connection.commit();

	}

	@Override
	public void loadData(CSVParser csvContent) throws SQLException {
		for (CSVRecord row : csvContent) {
			int idProducto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			float valor = Float.parseFloat(row.get("valor"));
			Producto p = new Producto(idProducto, nombre, valor);
			this.save(p);
		}
	}

}
