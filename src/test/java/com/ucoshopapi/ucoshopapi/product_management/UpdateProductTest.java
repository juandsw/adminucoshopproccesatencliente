package com.ucoshopapi.ucoshopapi.product_management;

import com.ucoshopapi.ucoshopapi.domain.product_management.ProductDomain;
import com.ucoshopapi.ucoshopapi.services.product_management.ProductService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateProductTest {

    private final ProductService productService = new ProductService();

    @Test
    void productIsNullTest() {
        ProductDomain product = new ProductDomain();
        product.setProductId(UUID.randomUUID());

        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.updateProduct(product.getProductId(), null)
        );

        assertEquals("No se puede actualizar el producto ya que la información se encuentra nula o vacía.", exception.getMessage());
    }

    @Test
    void productIsEmptyTest() {
        ProductDomain product = new ProductDomain();
        product.setProductId(UUID.randomUUID());

        ProductDomain productEmpty = new ProductDomain();
        productEmpty.setPrice(0.0);
        productEmpty.setStock(0);

        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.updateProduct(product.getProductId(), productEmpty)
        );

        assertEquals("No se puede actualizar el producto ya que la información se encuentra nula o vacía.", exception.getMessage());
    }

}
