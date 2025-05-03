package com.ucoshopapi.ucoshopapi.product_management;

import com.ucoshopapi.ucoshopapi.domain.product_management.ProductDomain;
import com.ucoshopapi.ucoshopapi.services.product_management.ProductService;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatchProductTest {

    private final ProductService productService = new ProductService();


    @Test
    void productIsNullTest() {
        UUID productId = UUID.randomUUID();

        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.patchProduct(productId, null)
        );

        assertEquals("No se puede actualizar el producto ya que la información se encuentra nula o vacía.", exception.getMessage());
    }

    @Test
    void productIsEmptyTest() {
        UUID productId = UUID.randomUUID();

        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.patchProduct(productId, Collections.emptyMap())
        );

        assertEquals("No se puede actualizar el producto ya que la información se encuentra nula o vacía.", exception.getMessage());
    }
}
