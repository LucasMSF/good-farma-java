package br.goodfarma.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnHello;

    public void initialize() {
        btnHello.setOnAction(actionEvent -> {
            System.out.println("Clicked!");
        });
    }
}