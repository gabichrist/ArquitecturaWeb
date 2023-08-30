package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;

public interface Dao<T> {
    
    Optional<T> get(long id) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    void save(T t) throws SQLException;
    
    void update(T t, String[] params);
    
    void delete(T t);
    
    void openConnection();
    
    void createTable() throws SQLException;
    
    void loadData(CSVParser csvContent) throws SQLException;
}