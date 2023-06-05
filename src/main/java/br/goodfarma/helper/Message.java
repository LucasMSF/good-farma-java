package br.goodfarma.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Message {
    public static void show(String texto, Alert.AlertType type) {
        Alert alert = new Alert(type, texto, ButtonType.OK);
        alert.showAndWait();
    }
}
