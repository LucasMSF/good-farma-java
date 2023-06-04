package br.goodfarma;

import br.goodfarma.enumerable.Views;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;

        Font.loadFont(this.getClass().getResourceAsStream("style/font/Roboto-Regular.ttf"), 14);

            MainApplication.navigate(Views.EMPLOY);
        stage.show();
    }

    public static void navigate(Views view) {
        MainApplication.stage.setTitle(view.getTitle());
        MainApplication.stage.setScene(view.getView());
    }

    public static void main(String[] args) {
        launch();
    }

}