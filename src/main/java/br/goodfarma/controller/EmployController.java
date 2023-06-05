package br.goodfarma.controller;

import br.goodfarma.dao.EmployDao;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.Validation;
import br.goodfarma.model.Employ;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;

public class EmployController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;

    public void initialize() {

    }

    public void onBtnSaveClick() {
        if (!Validation.validateEmpty(
                this.txtName.getText(),
                this.txtCpf.getText(),
                this.txtTelephone.getText(),
                this.txtLogin.getText(),
                this.txtPassword.getText())) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
        }

        Employ newEmploy = new Employ(
                this.txtName.getText(),
                this.txtCpf.getText(),
                this.txtTelephone.getText(),
                this.txtLogin.getText(),
                this.txtPassword.getText()
        );

        try {
            if((new EmployDao()).insert(newEmploy)) {
                Message.show("Funcionário inserido com sucesso!", Alert.AlertType.INFORMATION);

            } else {
                Message.show("Erro ao inserir funcionário", Alert.AlertType.ERROR);
            }
        } catch(SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }
    }
}
