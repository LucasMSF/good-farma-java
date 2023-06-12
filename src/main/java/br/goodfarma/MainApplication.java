package br.goodfarma;

import br.goodfarma.enumerable.Views;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private static Stage stage;
    public static String nowTitle;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;

        Font.loadFont(this.getClass().getResourceAsStream("style/font/Roboto-Regular.ttf"), 14);

        MainApplication.navigate(Views.AUTH);
        stage.show();
    }

    public static void navigate(Views view) {
        nowTitle = view.getTitle();
        setTitle(nowTitle);
        MainApplication.stage.setScene(view.getView());
    }

    public static void setTitle(String title) {
        MainApplication.stage.setTitle(title);
    }

    public static void main(String[] args) {
        launch();
    }

}