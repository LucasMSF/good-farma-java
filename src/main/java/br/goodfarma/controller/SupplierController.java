package br.goodfarma.controller;

import br.goodfarma.dao.Dao;
import br.goodfarma.dao.ProductDao;
import br.goodfarma.dao.SupplierDao;
import br.goodfarma.helper.Message;
import br.goodfarma.helper.validation.IntegerFormatter;
import br.goodfarma.helper.validation.Validation;
import br.goodfarma.model.Product;
import br.goodfarma.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.*;

public class SupplierController extends CrudController<Supplier> {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCnpj;
    @FXML
    private TextField txtTelephone;
    @FXML
    private ComboBox<String> cmbState;
    @FXML
    private ListView<Product> listProducts;

    private final Dao<Supplier> supplierDao = new SupplierDao();
    private final Dao<Product> productDao = new ProductDao();

    private Collection<Product> products;

    public void clear() {
        this.txtName.setText(null);
        this.txtCnpj.setText("");
        this.txtTelephone.setText("");
        this.cmbState.setValue(null);
        this.listProducts.getSelectionModel().clearSelection();
    }

    private void startCombo() throws SQLException {
        ObservableList<String> states = FXCollections.observableArrayList(
                "AC", // Acre
                "AL", // Alagoas
                "AP", // Amapá
                "AM", // Amazonas
                "BA", // Bahia
                "CE", // Ceará
                "DF", // Distrito Federal
                "ES", // Espírito Santo
                "GO", // Goiás
                "MA", // Maranhão
                "MT", // Mato Grosso
                "MS", // Mato Grosso do Sul
                "MG", // Minas Gerais
                "PA", // Pará
                "PB", // Paraíba
                "PR", // Paraná
                "PE", // Pernambuco
                "PI", // Piauí
                "RJ", // Rio de Janeiro
                "RN", // Rio Grande do Norte
                "RS", // Rio Grande do Sul
                "RO", // Rondônia
                "RR", // Roraima
                "SC", // Santa Catarina
                "SP", // São Paulo
                "SE", // Sergipe
                "TO"  // Tocantins
        );
        this.cmbState.setItems(states);
    }

    private void startList() throws SQLException {
        this.listProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.products = productDao.findAll("");
        ObservableList<Product> products = FXCollections.observableArrayList(this.products);
        this.listProducts.setItems(products);
    }

    private void selectListItems(Collection<Product> items) {
        List<Product> products = (List<Product>) this.products;
        for (Product item : items) {
            int index = products.indexOf(item);
            if (index >= 0) {
                this.listProducts.getSelectionModel().select(index);
            }
        }
    }

    public void initialize() {
        try {
            this.txtCnpj.setTextFormatter(new TextFormatter<>(new IntegerFormatter()));
            this.txtTelephone.setTextFormatter(new TextFormatter<>(new IntegerFormatter()));
            super.initialize();
            this.startCombo();
            this.startList();

        } catch (Exception e) {
            Message.show("Erro ao inicializar", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void onBtnSaveClick() {
        String state = Optional.ofNullable(this.cmbState.getValue()).orElse("");
        ObservableList<Product> products = this.listProducts.getSelectionModel().getSelectedItems();
        Collection<Product> cloneProducts = new ArrayList<>(products);
        for (Product product: products) {
            cloneProducts.add(product.clone());
        }

        if (!Validation.validateEmpty(
                this.txtName.getText(),
                this.txtCnpj.getText(),
                this.txtTelephone.getText(),
                state
        )) {

            Message.show("Preencha todos os campos corretamente", Alert.AlertType.ERROR);
            return;
        }

        Supplier supplier = new Supplier(
                this.txtName.getText(),
                this.txtCnpj.getText(),
                state,
                this.txtTelephone.getText(),
                cloneProducts
        );

        try {
            if (this.isEditing) {
                if (this.supplierDao.update(supplier)) {
                    Message.show("Registro alterado com sucesso!", Alert.AlertType.INFORMATION);

                } else {
                    Message.show("Erro ao alterar registro", Alert.AlertType.ERROR);
                }
            } else {
                if (this.supplierDao.insert(supplier)) {
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
        if (!Validation.validateEmpty(this.txtCnpj.getText())) {
            Message.show("Preencha o campo CNPJ corretamente", Alert.AlertType.ERROR);
            return;
        }

        try {
            Supplier findedSupplier = this.supplierDao.findById(new Supplier(this.txtCnpj.getText()));
            if (findedSupplier == null) {
                Message.show("Erro ao encontrar registro", Alert.AlertType.ERROR);
            } else {
                this.txtName.setText(findedSupplier.getName());
                this.txtCnpj.setText(findedSupplier.getCnpj());
                this.txtTelephone.setText(findedSupplier.getTelephone());
                this.cmbState.setValue(findedSupplier.getState());
                this.selectListItems(findedSupplier.getProducts());
                this.isEditing(true);
            }
        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }
    }

    public void onBtnDeleteClick() {
        if (!Message.confirm("Realmente deseja excluir esse registro?")) {
            return;
        }

        try {
            Supplier findedSupplier = this.supplierDao.findById(new Supplier(this.txtCnpj.getText()));
            if (this.supplierDao.delete(findedSupplier)) {
                Message.show("Registro excluído com sucesso!", Alert.AlertType.INFORMATION);

            } else {
                Message.show("Erro ao excluir registro", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            Message.show("Erro no banco de dados", Alert.AlertType.ERROR);
        }

        this.onBtnCancelClick();
    }
}
