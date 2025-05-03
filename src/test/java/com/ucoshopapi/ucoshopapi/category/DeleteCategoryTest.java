package com.ucoshopapi.ucoshopapi.category;

import com.ucoshopapi.ucoshopapi.services.category.CategoryService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeleteCategoryTest {

    private final CategoryService categoryService = new CategoryService(null, null); // Sin repo, solo prueba validaciones

    @Test
    void deleteCategory_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.deleteCategory(null);
        });
        assertEquals("El id es obligatorio.", exception.getMessage());
    }
}
