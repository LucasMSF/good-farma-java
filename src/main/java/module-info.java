module com.example.goodfarma {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires org.controlsfx.controls;
    requires java.sql;


    opens br.goodfarma to javafx.fxml;
    exports br.goodfarma;
    exports br.goodfarma.controller;
    opens br.goodfarma.controller to javafx.fxml;
    exports br.goodfarma.helper;
    opens br.goodfarma.helper to javafx.fxml;
}