package com.company.service.test;

import com.company.model.Product;
import com.company.model.ProductDiscount;
import com.company.service.ProductsService;
import com.company.message.Message;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ProductsServiceTest {

    public ProductsService productsService;

    @ParameterizedTest
    @MethodSource("provideInputs1")
    public void shouldThrowProperExceptionsWhenInvalidOutputsAreProvided(String expectedMessage, List<Product> products, int discount) {
        this.productsService = new ProductsService();
        Exception exception = assertThrows(Exception.class, () -> {
            productsService.validateInput(products, discount);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("provideInputs2")
    public void shouldNotCalculateProductsDiscountsWhenInvalidInputIsProvided(List<Product> products, int discount, List<ProductDiscount> expectedList) throws Exception {
        this.productsService = new ProductsService();
        List<ProductDiscount> productDiscounts = productsService.calculateProductsDiscounts(products, discount);
        assertThat(productDiscounts).hasSameElementsAs(expectedList);
    }

    private static Stream<Arguments> provideInputs1() {
        return Stream.of(
                Arguments.arguments(Message.LIST_EMPTY_OR_NULL, List.of(), 10),
                Arguments.arguments(Message.APP_UP_TO_5_PROD, List.of(
                                new Product(10, "1"),
                                new Product(10, "2"),
                                new Product(10, "3"),
                                new Product(10, "4"),
                                new Product(10, "5"),
                                new Product(10, "6")),
                        10),
                Arguments.arguments(Message.DISCOUNT_INVALID_PRICE, List.of(new Product(9, "1")), 0));
    }

    private static Stream<Arguments> provideInputs2() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Product(500, "product1"),
                        new Product(1500, "product2")), 100,
                        List.of(
                                new ProductDiscount(new Product(500, "product1"), 25),
                                new ProductDiscount(new Product(1500, "product2"), 75))),
                Arguments.arguments(List.of(
                        new Product(600, "product1"),
                        new Product(1500, "product2")), 100,
                        List.of(
                        new ProductDiscount(new Product(600, "product1"), 28.57),
                        new ProductDiscount(new Product(1500, "product2"), 71.43))),
                Arguments.arguments(List.of(
                        new Product(80, "product1"),
                        new Product(8000, "product2")),22,
                        List.of(
                                new ProductDiscount(new Product(80, "product1"), 0.21),
                                new ProductDiscount(new Product(8000, "product2"), 21.79)))
        );
    }
}
