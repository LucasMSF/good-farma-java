package br.goodfarma.enumerable;

import br.goodfarma.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public enum Views {
    MENU("Menu", "menu"),
    HELLO("Hello", "hello");

    private final String title;
    private final Scene view;
    Views(String title, String viewName) {
        this.title = title;

        try {
            FXMLLoader menuLoader = new FXMLLoader(MainApplication.class.getResource("view/" + viewName + "-view.fxml"));
            this.view = new javafx.scene.Scene(menuLoader.load(), 1000, 500);
            this.view.getStylesheets().add(MainApplication.class.getResource("style/style.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException("Error on load scene");
        }
    }

    public String getTitle() {
        return "GoodFarma - " + title;
    }

    public Scene getView() {
        return view;
    }
}
