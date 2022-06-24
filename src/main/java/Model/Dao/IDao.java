package Model.Dao;

import Model.ConnectionPool;

import javax.sql.DataSource;
import java.util.ArrayList;

public interface IDao<T> {
    DataSource dataSource = ConnectionPool.getDatasource();
    T findById(Integer id);
    ArrayList<T> findAll();
    void add(T entity);
    void delete(Integer id);
    void update(T entity);
}
