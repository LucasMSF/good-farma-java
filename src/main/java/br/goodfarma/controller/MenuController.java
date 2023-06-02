package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.enumerable.Views;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {
    @FXML
    private Button helloButton;

    @FXML
    protected void onHelloButtonClick() {
        MainApplication.navigate(Views.HELLO);
    }
}