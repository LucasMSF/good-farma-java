package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.Session;
import br.goodfarma.enumerable.Views;
import br.goodfarma.helper.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SiderbarController {
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblUserDocument;

    public void onBtnBackClick() {
        MainApplication.navigate(Views.MENU);
    }

    public void onBtnExitClick() {
        if(!Message.confirm("Deseja realmente sair?")) {
            return;
        }
        Session.forget("user-name");
        Session.forget("user-document");
        MainApplication.navigate(Views.AUTH);
    }
    public void initialize() {
        this.lblUserName.setText(Session.get("user-name"));
        this.lblUserDocument.setText(Session.get("user-document"));
    }
}
