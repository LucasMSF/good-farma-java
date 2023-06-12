package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.Session;
import br.goodfarma.dao.Dao;
import br.goodfarma.dao.EmployDao;
import br.goodfarma.database.Database;
import br.goodfarma.enumerable.Views;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.validation.Validation;
import br.goodfarma.model.Employ;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;

    private final Dao<Employ> employDao = new EmployDao();

    private void clear() {
        this.txtLogin.setText(null);
        this.txtPassword.setText(null);
    }

    private Employ auth(String login, String password) {
        try {
            Database.connect();
            String sql = "SELECT * FROM employees WHERE login = ? and password = ?";
            PreparedStatement pst = Database.getConnection().prepareStatement(sql);

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
           if(rs.next()) {
                return employDao.findById(new Employ(rs.getString("cpf")));
           } else {
               return null;
           }
        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
            return null;
        }
    }

    public void onBtnAuthClick() {
        if (!Validation.validateEmpty(
                this.txtLogin.getText(),
                this.txtLogin.getText())) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
            return;
        }

        Employ employ = this.auth(txtLogin.getText(), txtPassword.getText());

        if(employ != null) {
            Session.put("user-name", employ.getName());
            Session.put("user-document", employ.getCpf());
            MainApplication.navigate(Views.MENU);
        } else {
            Message.show("Usuário ou senha incorretos!", Alert.AlertType.ERROR);
        }
    }
}
