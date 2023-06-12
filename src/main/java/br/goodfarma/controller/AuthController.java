package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.database.Database;
import br.goodfarma.enumerable.Views;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.validation.Validation;
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

    private void clear() {
        this.txtLogin.setText(null);
        this.txtPassword.setText(null);
    }

    private boolean auth(String login, String password) {
        try {
            Database.connect();
            String sql = "SELECT * FROM employees WHERE login = ? and password = ?";
            PreparedStatement pst = Database.getConnection().prepareStatement(sql);

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
            return false;
        }
    }

    public void onBtnAuthClick() {
        if (!Validation.validateEmpty(
                this.txtLogin.getText(),
                this.txtLogin.getText())) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
            return;
        }

        if(auth(txtLogin.getText(), txtPassword.getText())) {
            this.clear();
            MainApplication.navigate(Views.MENU);
        } else {
            Message.show("Usuário ou senha incorretos!", Alert.AlertType.ERROR);
        }
    }
}
