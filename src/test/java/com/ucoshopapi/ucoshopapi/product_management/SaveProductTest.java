package com.ucoshopapi.ucoshopapi.product_management;

import com.ucoshopapi.ucoshopapi.domain.product_management.ProductDomain;
import com.ucoshopapi.ucoshopapi.services.product_management.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaveProductTest {

    private final ProductService productService = new ProductService();

    @Test
    void productNameIsNullTest() {
        ProductDomain product = new ProductDomain();

        product.setName(null);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(product);
        });

        assertEquals("El nombre del producto no puede ser nulo.", exception.getMessage());
    }

    @Test
    void productNameIsEmptyTest() {
        ProductDomain product = new ProductDomain();
        product.setName("");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(product);
        });

        assertEquals("El nombre del producto no puede ser nulo.", exception.getMessage());
    }
}
