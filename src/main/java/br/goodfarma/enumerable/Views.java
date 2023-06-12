package br.goodfarma.enumerable;

import br.goodfarma.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public enum Views {
    MENU("Menu", "menu"),
    AUTH("Autenticação", "auth", 450, 600),
    EMPLOY("Funcionários", "employ"),
    PRODUCT("Produtos", "product"),
    SUPPLIER("Fornecedores", "supplier"),
    PRODUCT_TABLE("Tabela de produtos", "product-table");

    private final String title;
    private final Scene view;
    Views(String title, String viewName, int width, int height) {
        this.title = title;

        try {
            FXMLLoader sceneLoader = new FXMLLoader(MainApplication.class.getResource("view/" + viewName + "-view.fxml"));
            this.view = new javafx.scene.Scene(sceneLoader.load(), width, height);
            this.view.getStylesheets().add(MainApplication.class.getResource("style/style.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException("Error on load scene");
        }
    }
    Views(String title, String viewName) {
        this(title, viewName, 1000, 650);
    }

    public String getTitle() {
        return "GoodFarma - " + title;
    }

    public Scene getView() {
        return view;
    }
}
