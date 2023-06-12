package br.goodfarma.model;

import java.util.Collection;
import java.util.Objects;

public class Supplier {
    private int id;
    private String name;
    private String cnpj;
    private String state;
    private String telephone;

    private Collection<Product> products;

    public Supplier(String name, String cnpj, String state, String telephone, Collection<Product> products) {
        this.name = name;
        this.cnpj = cnpj;
        this.state = state;
        this.telephone = telephone;
        this.products = products;
    }

    public Supplier(int id, String name, String cnpj, String state, String telephone) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.state = state;
        this.telephone = telephone;
    }

    public Supplier(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(cnpj, supplier.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
