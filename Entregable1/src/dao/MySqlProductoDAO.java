package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entity.Producto;
import entity.ProductoConRecaudacion;
import factory.MySqlDAOFactory;

public class MySqlProductoDAO implements DaoProducto {

	private Connection connection;

	public MySqlProductoDAO() throws SQLException {
		this.openConnection();
		this.createTable();
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

	@Override
	public ProductoConRecaudacion getProductoMasRecaudado() throws SQLException {
		connection = MySqlDAOFactory.getConnection();
		ProductoConRecaudacion productoMasRecaudado = null;

		// Recaudacion: cantidad de productos vendidos multiplicado por su valor.
		String producto = "SELECT p.*, SUM(p.valor * fp.cantidad) as totalRecaudacion "
				+ "FROM producto p NATURAL JOIN facturaproducto fp " + "GROUP BY idProducto "
				+ "ORDER BY `totalRecaudacion` DESC " + "LIMIT 1";

		PreparedStatement ps = connection.prepareStatement(producto);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int idProducto = rs.getInt(1);
			String nombre = rs.getString(2);
			Float valor = rs.getFloat(3);
			Float recaudado = rs.getFloat(4);
			productoMasRecaudado = new ProductoConRecaudacion(idProducto, nombre, valor, recaudado);
		}

		ps.close();
		rs.close();
		connection.close();

		return productoMasRecaudado;
	}
}
