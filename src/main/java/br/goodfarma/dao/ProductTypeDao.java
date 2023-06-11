package br.goodfarma.dao;

import br.goodfarma.database.Database;
import br.goodfarma.model.Product;
import br.goodfarma.model.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductTypeDao implements Dao<ProductType> {
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insert(ProductType model) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(ProductType model) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ProductType model) throws SQLException {
        return false;
    }

    @Override
    public ProductType findById(ProductType model) throws SQLException {
        String sql = "SELECT * FROM product_types WHERE id = ?";

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();
        ProductType first = null;

        if (rs.next()) {
            first = new ProductType(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("id")
            );
        }

        Database.disconnect();

        return first;
    }

    @Override
    public Collection<ProductType> findAll(String rawFilter) throws SQLException {
        Collection<ProductType> list = new ArrayList<>();

        String sql = "SELECT * FROM product_types ";

        if (rawFilter.length() != 0) {
            sql += "WHERE " + rawFilter;
        }

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        while (rs.next()) {
            list.add(
                    new ProductType(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("id")
                    )
            );
        }

        Database.disconnect();

        return list;
    }
}
