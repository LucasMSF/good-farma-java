package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.dao.Dao;
import br.goodfarma.dao.EmployDao;
import br.goodfarma.enumerable.Validations;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.Validation;
import br.goodfarma.model.Employ;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    private final Dao<Employ> employDao = new EmployDao();
    private boolean isEditing = false;

    private void isEditing(boolean bool) {
        if(bool) {
            MainApplication.setTitle(MainApplication.nowTitle + " (Editando...)");
        } else {
            MainApplication.setTitle(MainApplication.nowTitle);
        }
        this.isEditing = bool;
        btnCancel.setVisible(bool);
        btnDelete.setVisible(bool);
    }
    private void clear() {
        this.txtName.setText(null);
        this.txtCpf.setText("");
        this.txtTelephone.setText("");
        this.txtLogin.setText(null);
        this.txtPassword.setText(null);
    }
    public void initialize() {
        this.isEditing(false);
    }

    public void onBtnSaveClick() {
        if (!Validation.validateEmpty(
                this.txtName.getText(),
                this.txtCpf.getText(),
                this.txtTelephone.getText(),
                this.txtLogin.getText(),
                this.txtPassword.getText())) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
            return;
        }

        if(
            !Validation.validateWithRegex(Validations.CPF, this.txtCpf.getText()) ||
            !Validation.validateWithRegex(Validations.TELEPHONE, this.txtTelephone.getText())
        ) {
            Message.show("Os campos não estão no formato correto", Alert.AlertType.ERROR);
            return;
        }

        Employ employ = new Employ(
                this.txtName.getText(),
                this.txtCpf.getText(),
                this.txtTelephone.getText(),
                this.txtLogin.getText(),
                this.txtPassword.getText()
        );

        try {
            if(this.isEditing) {
                if(this.employDao.update(employ)) {
                    Message.show("Registro alterado com sucesso!", Alert.AlertType.INFORMATION);

                } else {
                    Message.show("Erro ao alterar registro", Alert.AlertType.ERROR);
                }
            } else {
                if(this.employDao.insert(employ)) {
                    Message.show("Registro inserido com sucesso!", Alert.AlertType.INFORMATION);

                } else {
                    Message.show("Erro ao inserir registro", Alert.AlertType.ERROR);
                }
            }

        } catch(SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }

        this.onBtnCancelClick();
    }

    public void onBtnFindClick() {
        if (!Validation.validateEmpty(this.txtCpf.getText())) {
            Message.show("Preencha o campo CPF corretamente", Alert.AlertType.ERROR);
            return;
        }

        try {
            Employ findedEmploy = employDao.findById(new Employ(this.txtCpf.getText()));
            if(findedEmploy == null) {
                Message.show("Erro ao encontrar registro", Alert.AlertType.ERROR);
            } else {
                this.txtName.setText(findedEmploy.getName());
                this.txtCpf.setText(findedEmploy.getCpf());
                this.txtTelephone.setText(findedEmploy.getTelephone());
                this.txtLogin.setText(findedEmploy.getLogin());
                this.txtPassword.setText(findedEmploy.getPassword());
                this.isEditing(true);
            }
        } catch(SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }
    }

    public void onBtnDeleteClick() {
        if(!Message.confirm("Realmente deseja excluir esse registro?")) {
            return;
        }

        try {
            Employ findedEmploy = this.employDao.findById(new Employ(this.txtCpf.getText()));
            if(this.employDao.delete(findedEmploy)) {
                Message.show("Registro excluído com sucesso!", Alert.AlertType.INFORMATION);

            } else {
                Message.show("Erro ao excluir registro", Alert.AlertType.ERROR);
            }
        } catch(SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }

        this.onBtnCancelClick();
    }
    public void onBtnCancelClick() {
        this.isEditing(false);
        this.clear();
    }
}
