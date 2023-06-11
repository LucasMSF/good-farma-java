package br.goodfarma.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class Message {
    public static void show(String text, Alert.AlertType type) {
        Alert alert = new Alert(type, text, ButtonType.OK);
        alert.showAndWait();
    }

    public static boolean confirm(String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Confirme a ação");
        alert.setContentText(question);

        Button okButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);
        okButton.setText("Sim");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.CANCEL);
        cancelButton.setText("Não");

        ButtonType res = alert.showAndWait().orElse(javafx.scene.control.ButtonType.CANCEL);
        return res == ButtonType.OK;
    }
}
