package com.company.service;

import com.company.exception.DiscountException;
import com.company.exception.ProductException;
import com.company.model.Product;
import com.company.model.ProductDiscount;
import com.company.util.ListUtils;
import com.company.message.Message;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.util.ProductDiscountUtil.calculateDiscount;

public class ProductsService {
    public List<ProductDiscount> calculateProductsDiscounts(List<Product> products, int discount) throws Exception {
        validateInput(products, discount);
        int sumOfProductsPrices = products.stream().map(Product::getProductPrice).reduce(0, Integer::sum);
        List<ProductDiscount> productsDiscounts = products.stream()
                .map(product -> new ProductDiscount(product, calculateDiscount(sumOfProductsPrices, discount, product.getProductPrice())))
                .collect(Collectors.toList());
        //handle case when rounding will change our discount to value less than discount
        double sumOfDiscountPrices = productsDiscounts.stream().map(ProductDiscount::getDiscount).reduce(0.0, Double::sum);
        if(discount - sumOfDiscountPrices > 0) {
            int productsDiscountsLastIndex = productsDiscounts.size()-1;
            double lastDiscountValue = productsDiscounts.get(productsDiscountsLastIndex).getDiscount();
            productsDiscounts.get(productsDiscountsLastIndex).setDiscount(lastDiscountValue + discount - sumOfDiscountPrices);
        }
        return productsDiscounts;
    }

    public void validateInput(List<Product> products, int discount) throws Exception {
        if (ListUtils.isNullOrEmpty(products)) {
            throw new ProductException(Message.LIST_EMPTY_OR_NULL);
        }
        if (products.size() > 5) {
            throw new ProductException(Message.APP_UP_TO_5_PROD);
        }
        // I assume that product can't be free and discount can't be 0
        for (Product product : products) {
            if (product.getProductPrice() <= 0) {
                throw new ProductException((String.format(Message.PRODUCT_INVALID_PRICE, product.getProductName())));
            }
        }
        if (discount <= 0) {
            throw new DiscountException(Message.DISCOUNT_INVALID_PRICE);
        }
    }
}
