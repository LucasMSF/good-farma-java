package br.goodfarma.controller;

import br.goodfarma.dao.Dao;
import br.goodfarma.dao.ProductDao;
import br.goodfarma.helper.Message;
import br.goodfarma.model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductTableController {
    @FXML
    private TableView<Product> tviewProduct;
    @FXML
    private TextField txtSearch;

    private final Dao<Product> productDao = new ProductDao();

    private void setTableView(String query) {
        try {
            ObservableList<Product> products = FXCollections.observableArrayList(this.productDao.findAll(query));
            this.tviewProduct.setItems(products);
        } catch(Exception e) {
            Message.show("Erro ao buscar os dados", Alert.AlertType.ERROR);
        }

    }

    public void initialize() {
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(resource -> new SimpleIntegerProperty(resource.getValue().getId()).asObject());

        TableColumn<Product, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(resource -> new SimpleStringProperty(resource.getValue().getName()));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantidade");
        quantityColumn.setCellValueFactory(resource -> new SimpleIntegerProperty(resource.getValue().getQuantity()).asObject());

        TableColumn<Product, String> typeColumn = new TableColumn<>("Tipo do produto");
        typeColumn.setCellValueFactory(resource -> new SimpleStringProperty(resource.getValue().getProductType().getName()));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Preço");
        priceColumn.setCellValueFactory(resource -> new SimpleDoubleProperty(resource.getValue().getPrice()).asObject());

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Descrição");
        descriptionColumn.setCellValueFactory(resource -> new SimpleStringProperty(resource.getValue().getDescription()));

        tviewProduct.getColumns().addAll(idColumn, nameColumn, quantityColumn, typeColumn, priceColumn, descriptionColumn);

        this.setTableView("");

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            this.setTableView("name like '" + newValue + "%'");
        });

    }
}
