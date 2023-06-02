module com.example.goodfarma {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;


    opens br.goodfarma to javafx.fxml;
    exports br.goodfarma;
    exports br.goodfarma.controller;
    opens br.goodfarma.controller to javafx.fxml;
}