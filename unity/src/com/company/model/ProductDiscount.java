package com.company.model;

public class ProductDiscount extends Product {
    private double discount;

    public ProductDiscount(Product product, double discount) {
        super(product.getProductPrice(), product.getProductName());
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
