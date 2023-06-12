package br.goodfarma.dao;

import br.goodfarma.model.Supplier;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Essa Dao armazena os dados em coleções e não envia para o banco de dados
 */
public class SupplierDao implements Dao<Supplier>{
    private final List<Supplier> collection;

    public SupplierDao() {
        this.collection = new ArrayList<>();
    }

    @Override
    public boolean insert(Supplier model) throws SQLException {
        return this.collection.add(model);
    }

    @Override
    public boolean delete(Supplier model) throws SQLException {
        return this.collection.remove(model);
    }

    @Override
    public boolean update(Supplier model) throws SQLException {
        this.delete(model);
        return this.collection.add(model);
    }

    @Override
    public Supplier findById(Supplier model) throws SQLException {
        int index = this.collection.indexOf(model);
        if(index < 0) return null;
        return this.collection.get(index);
    }

    @Override
    public Collection<Supplier> findAll(String rawFilter) throws SQLException {
        return this.collection;
    }
}
