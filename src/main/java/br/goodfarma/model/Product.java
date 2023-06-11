package br.goodfarma.model;

public class Product {
    public int id;
    private String name;
    private int quantity;
    private ProductType productType;
    private double price;
    private String description;

    public Product(String name, int quantity, ProductType productType, double price, String description, int id) {
        this.name = name;
        this.quantity = quantity;
        this.productType = productType;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public Product(String name, int quantity, ProductType productType, double price, String description) {
        this.name = name;
        this.quantity = quantity;
        this.productType = productType;
        this.price = price;
        this.description = description;
    }

    public Product(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
