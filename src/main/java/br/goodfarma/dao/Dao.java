package br.goodfarma.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface Dao<T>{
    public boolean insert(T model) throws SQLException;
    public boolean delete(T model) throws SQLException;
    public boolean update(T model) throws SQLException;
    public T findById(T model) throws SQLException;
    public Collection <T> findAll(String rawFilter) throws SQLException;
}
