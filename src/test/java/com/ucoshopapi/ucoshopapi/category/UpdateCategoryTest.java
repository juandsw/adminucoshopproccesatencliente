package com.ucoshopapi.ucoshopapi.category;

import com.ucoshopapi.ucoshopapi.domain.category.CategoryDomain;
import com.ucoshopapi.ucoshopapi.services.category.CategoryService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCategoryTest {

    private final CategoryService categoryService = new CategoryService(null, null); // Sin repo, solo validaciones

    @Test
    void updateCategory_ShouldThrowException_WhenIdIsNull() {
        CategoryDomain category = new CategoryDomain();
        category.setName("Electronics");
        category.setDescription("Devices and gadgets");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(category);
        });

        assertEquals("El idCategoria es obligatorio.", exception.getMessage());
    }

    @Test
    void updateCategory_ShouldThrowException_WhenNameIsNull() {
        CategoryDomain category = new CategoryDomain();
        category.setIdCategory(UUID.randomUUID());
        category.setName(null);
        category.setDescription("Description");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(category);
        });

        assertEquals("El nombre de la categoría es obligatorio.", exception.getMessage());
    }

    @Test
    void updateCategory_ShouldThrowException_WhenNameIsEmpty() {
        CategoryDomain category = new CategoryDomain();
        category.setIdCategory(UUID.randomUUID());
        category.setName("");
        category.setDescription("Description");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(category);
        });

        assertEquals("El nombre de la categoría es obligatorio.", exception.getMessage());
    }


    @Test
    void updateCategory_ShouldThrowException_WhenDescriptionIsNull() {
        CategoryDomain category = new CategoryDomain();
        category.setIdCategory(UUID.randomUUID());
        category.setName("Electronics");
        category.setDescription(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(category);
        });

        assertEquals("La descripción de la categoría es obligatoria.", exception.getMessage());
    }


    @Test
    void updateCategory_ShouldThrowException_WhenDescriptionIsEmpty() {
        CategoryDomain category = new CategoryDomain();
        category.setIdCategory(UUID.randomUUID());
        category.setName("Electronics");
        category.setDescription("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(category);
        });

        assertEquals("La descripción de la categoría es obligatoria.", exception.getMessage());
    }
}

