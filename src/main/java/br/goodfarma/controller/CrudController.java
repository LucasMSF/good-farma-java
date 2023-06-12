package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class CrudController<M> implements Crud {
    @FXML
    protected Button btnCancel;
    @FXML
    protected Button btnDelete;
    protected boolean isEditing = false;
    protected M model;
    protected void isEditing(M model) {
        MainApplication.setTitle(MainApplication.nowTitle + " (Editando...)");
        MainApplication.setTitle(MainApplication.nowTitle);

        this.isEditing = true;
        this.model = model;
        btnCancel.setVisible(true);
        btnDelete.setVisible(true);
    }

    protected void isEditing(boolean bool) {
        if (bool) {
            MainApplication.setTitle(MainApplication.nowTitle + " (Editando...)");
        } else {
            MainApplication.setTitle(MainApplication.nowTitle);
            this.model = null;
        }
        this.isEditing = bool;
        btnCancel.setVisible(bool);
        btnDelete.setVisible(bool);
    }

    public void initialize() {
        this.isEditing(false);
    }

    public void onBtnCancelClick() {
        this.isEditing(false);
        this.clear();
    }
}
