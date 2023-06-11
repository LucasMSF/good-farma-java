package br.goodfarma.dao;

import br.goodfarma.database.Database;
import br.goodfarma.model.Employ;
import br.goodfarma.model.Product;
import br.goodfarma.model.ProductType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDao implements Dao<Product> {
    private PreparedStatement pst;
    private ResultSet rs;
    private final ProductTypeDao productTypeDao = new ProductTypeDao();

    @Override
    public boolean insert(Product model) throws SQLException {
        Database.connect();

        String sql = "INSERT INTO products (name, quantity, product_type_id, price, description) values (?, ?, ?, ?, ?)";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setString(1, model.getName());
        pst.setInt(2, model.getQuantity());
        pst.setInt(3, model.getProductType().getId());
        pst.setDouble(4, model.getPrice());
        pst.setString(5, model.getDescription());

        boolean insert = pst.executeUpdate() > 0;

        Database.disconnect();

        return insert;
    }

    @Override
    public boolean delete(Product model) throws SQLException {
        Database.connect();

        String sql = "DELETE FROM products WHERE id = ?";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setInt(1, model.getId());

        boolean delete = pst.executeUpdate() > 0;

        Database.disconnect();

        return delete;
    }

    @Override
    public boolean update(Product model) throws SQLException {
        Database.connect();

        String sql = "UPDATE products SET name = ?, quantity = ?, product_type_id = ?, price = ?, description = ? WHERE id = ?";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setString(1, model.getName());
        pst.setInt(2, model.getQuantity());
        pst.setInt(3, model.getProductType().getId());
        pst.setDouble(4, model.getPrice());
        pst.setString(5, model.getDescription());
        pst.setInt(6, model.getId());

        boolean update = pst.executeUpdate() > 0;

        Database.disconnect();

        return update;
    }

    @Override
    public Product findById(Product model) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);
        pst.setInt(1, model.getId());

        rs = pst.executeQuery();
        Product first = null;

        if (rs.next()) {
            first = new Product(
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    this.productTypeDao
                            .findById(new ProductType(rs.getInt("product_type_id"))),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getInt("id")

            );
        }

        Database.disconnect();

        return first;
    }

    @Override
    public Collection<Product> findAll(String rawFilter) throws SQLException {
        Collection<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products ";

        if (rawFilter.length() != 0) {
            sql += "WHERE " + rawFilter;
        }

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        while (rs.next()) {
            list.add(
                    new Product(
                            rs.getString("name"),
                            rs.getInt("quantity"),
                            this.productTypeDao
                                    .findById(new ProductType(rs.getInt("product_type_id"))),
                            rs.getDouble("price"),
                            rs.getString("description"),
                            rs.getInt("id")
                    )
            );
        }

        Database.disconnect();

        return list;
    }
}
