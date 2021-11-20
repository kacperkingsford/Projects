package com.company;

import com.company.exception.DiscountException;
import com.company.exception.ProductException;
import com.company.model.Product;
import com.company.model.ProductDiscount;
import com.company.service.ProductsService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Product> productList = List.of(new Product(500, "whiskey"), new Product(1500, "car"));
        ProductsService productsService = new ProductsService();
        try {
            List<ProductDiscount> productsDiscounts = productsService.calculateProductsDiscounts(productList, 100);
            for (ProductDiscount productDiscount : productsDiscounts) {
                System.out.println("(" + productDiscount.getProductName() + " price: " + productDiscount.getProductPrice() + " discount price: " + productDiscount.getDiscount() + ")");
            }
        } catch (ProductException | DiscountException e) {
            System.out.print("Wrong Input! " + e.getMessage());
        }
    }
}
