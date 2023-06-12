package br.goodfarma.controller;

import br.goodfarma.MainApplication;
import br.goodfarma.dao.ProductDao;
import br.goodfarma.dao.ProductTypeDao;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.validation.DoubleFormatter;
import br.goodfarma.helper.validation.IntegerFormatter;
import br.goodfarma.helper.validation.Validation;
import br.goodfarma.model.Product;
import br.goodfarma.model.ProductType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class ProductController extends CrudController<Product>{
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtQuantity;
    @FXML
    private ComboBox<ProductType> cmbProductType;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDescription;
    private final ProductTypeDao productTypeDao = new ProductTypeDao();
    private final ProductDao productDao = new ProductDao();

    private void startCombo() throws SQLException {
        ObservableList<ProductType> items = FXCollections.observableArrayList(this.productTypeDao.findAll(""));
        cmbProductType.setItems(items);
    }

    public void clear() {
        this.txtName.setText(null);
        this.txtQuantity.setText("");
        this.cmbProductType.setValue(null);
        this.txtPrice.setText(null);
        this.txtDescription.setText(null);
        this.txtId.setText(null);
    }

    public void initialize() {
        try {
            super.initialize();
            this.txtId.setTextFormatter(new TextFormatter<>(new IntegerFormatter()));
            this.txtQuantity.setTextFormatter(new TextFormatter<>(new IntegerFormatter()));
            this.txtPrice.setTextFormatter(new TextFormatter<>(new DoubleFormatter()));

            this.startCombo();

        } catch (Exception e) {
            Message.show("Erro ao inicializar", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void onBtnSaveClick() {
        ProductType productType = Optional.ofNullable(this.cmbProductType.getValue()).orElse(new ProductType("", ""));

        if (!Validation.validateEmpty(
                this.txtName.getText(),
                this.txtQuantity.getText(),
                productType.getName(),
                this.txtPrice.getText(),
                this.txtDescription.getText())) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
            return;
        }

        Product product = new Product(
                this.txtName.getText(),
                Integer.parseInt(txtQuantity.getText()),
                productType,
                Double.parseDouble(txtPrice.getText()),
                this.txtDescription.getText()
        );

        try {
            if (this.isEditing) {
                product.setId(this.model.id);
                if (this.productDao.update(product)) {
                    Message.show("Registro alterado com sucesso!", Alert.AlertType.INFORMATION);

                } else {
                    Message.show("Erro ao alterar registro", Alert.AlertType.ERROR);
                }
            } else {
                if (this.productDao.insert(product)) {
                    Message.show("Registro inserido com sucesso!", Alert.AlertType.INFORMATION);

                } else {
                    Message.show("Erro ao inserir registro", Alert.AlertType.ERROR);
                }
            }

        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }

        this.onBtnCancelClick();
    }

    public void onBtnFindClick() {
        if (!Validation.validateEmpty(this.txtId.getText())) {
            Message.show("Preencha o campo Identificador corretamente", Alert.AlertType.ERROR);
            return;
        }

        try {
            Product findedProduct = this.productDao.findById(new Product(Integer.parseInt(this.txtId.getText())));
            if (findedProduct == null) {
                Message.show("Erro ao encontrar registro", Alert.AlertType.ERROR);
            } else {
                this.txtName.setText(findedProduct.getName());
                this.txtQuantity.setText(String.valueOf(findedProduct.getQuantity()));
                this.cmbProductType.setValue(findedProduct.getProductType());
                this.txtPrice.setText(String.valueOf(findedProduct.getPrice()));
                this.txtDescription.setText(findedProduct.getDescription());
                this.isEditing(findedProduct);
            }
        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }
    }

    public void onBtnDeleteClick() {
        if(!Message.confirm("Realmente deseja excluir esse registro?")) {
            return;
        }

        try {
            if(this.productDao.delete(model)) {
                Message.show("Registro excluído com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                Message.show("Erro ao excluir registro", Alert.AlertType.ERROR);
            }
        } catch(SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }

        this.onBtnCancelClick();
    }

    public void onBtnCancelClick() {
        this.isEditing(false);
        this.clear();
    }
}

