package com.ucoshopapi.ucoshopapi.category;

import com.ucoshopapi.ucoshopapi.services.category.CategoryService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GetCategoryByIdTest {

    private final CategoryService categoryService = new CategoryService(null, null); // Sin dependencias externas


    @Test
    void getCategoryById_ShouldThrowException_WhenIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.getCategoryById(null);
        });

        assertEquals("El id es obligatorio.", exception.getMessage());
    }


}
