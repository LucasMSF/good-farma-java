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
    private final String viewName;
    private final int width;
    private final int height;
    private Scene view;
    Views(String title, String viewName, int width, int height) {
        this.title = title;
        this.viewName = viewName;
        this.width = width;
        this.height = height;

    }
    Views(String title, String viewName) {
        this(title, viewName, 1000, 650);
    }

    private Scene loadView() {
        try {
            FXMLLoader sceneLoader = new FXMLLoader(MainApplication.class.getResource("view/" + this.viewName + "-view.fxml"));
            Scene view =  new Scene(sceneLoader.load(), this.width, this.height);
            view.getStylesheets().add(MainApplication.class.getResource("style/style.css").toExternalForm());
            return view;
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException("Error on load scene");
        }
    }

    public String getTitle() {
        return "GoodFarma - " + title;
    }

    public Scene getView() {
        this.view = this.loadView();
        return view;
    }
}
