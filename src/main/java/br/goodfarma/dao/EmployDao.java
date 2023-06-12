package br.goodfarma.dao;

import br.goodfarma.database.Database;
import br.goodfarma.model.Employ;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployDao implements Dao<Employ> {
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insert(Employ model) throws SQLException {
        Database.connect();

        String sql = "INSERT INTO employees (name, cpf, telephone, login, password) values (?, ?, ?, ?, ?)";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setString(1, model.getName());
        pst.setString(2, model.getCpf());
        pst.setString(3, model.getTelephone());
        pst.setString(4, model.getLogin());
        pst.setString(5, model.getPassword());

        boolean insert = pst.executeUpdate() > 0;

        Database.disconnect();

        return insert;
    }

    @Override
    public boolean delete(Employ model) throws SQLException {
        Database.connect();

        String sql = "DELETE FROM employees WHERE cpf = ?";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setString(1, model.getCpf());

        boolean delete = pst.executeUpdate() > 0;

        Database.disconnect();

        return delete;
    }

    @Override
    public boolean update(Employ model) throws SQLException {
        Database.connect();

        String sql = "UPDATE employees SET name = ?, telephone = ?, login = ?, password = ?, cpf = ? WHERE id = ?";

        pst = Database.getConnection().prepareStatement(sql);

        pst.setString(1, model.getName()); //
        pst.setString(2, model.getTelephone());
        pst.setString(3, model.getLogin());
        pst.setString(4, model.getPassword());
        pst.setString(5, model.getCpf());
        pst.setInt(6, model.getId());

        boolean update = pst.executeUpdate() > 0;

        Database.disconnect();

        return update;
    }

    @Override
    public Employ findById(Employ model) throws SQLException {
        String sql = "SELECT * FROM employees WHERE cpf = ?";

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);
        pst.setString(1, model.getCpf());

        rs = pst.executeQuery();
        Employ first = null;

        if (rs.next()) {
            first = new Employ(
                    rs.getString("name"),
                    rs.getString("cpf"),
                    rs.getString("telephone"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getInt("id")
            );
        }

        Database.disconnect();

        return first;
    }

    @Override
    public Collection<Employ> findAll(String rawFilter) throws SQLException {
        Collection<Employ> list = new ArrayList<>();

        String sql = "SELECT * FROM employees ";

        if (rawFilter.length() != 0) {
            sql += "WHERE " + rawFilter;
        }

        Database.connect();

        pst = Database.getConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        while (rs.next()) {
            list.add(
                    new Employ(
                            rs.getString("name"),
                            rs.getString("cpf"),
                            rs.getString("telephone"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getInt("id")
                    )
            );
        }

        Database.disconnect();

        return list;
    }
}
