package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

public interface Dao<T> {

	List<T> getAll() throws SQLException;

	void save(T t) throws SQLException;

	void openConnection();

	void createTable() throws SQLException;

	void loadData(CSVParser csvContent) throws SQLException;
}