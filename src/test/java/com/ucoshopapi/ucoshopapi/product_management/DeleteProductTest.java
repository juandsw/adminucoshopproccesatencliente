package com.ucoshopapi.ucoshopapi.product_management;

import com.ucoshopapi.ucoshopapi.services.product_management.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteProductTest {

    private final ProductService productService = new ProductService();

    @Test
    void productIdIsNullTest() {
        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.deleteProduct(null)
        );
        assertEquals("El ID del producto a eliminar no puede ser nulo.", exception.getMessage());
    }

}
