package ;

import com.ucoshopapi.ucoshopapi.category.CategoryDomain;
import com.ucoshopapi.ucoshopapi.services.category.CategoryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaveCategoryTest {

    private final CategoryService categoryService = new CategoryService(null, null);

    @Test
    void saveCategory_ShouldThrowException_WhenNameIsNull() {
        CategoryDomain category = new CategoryDomain();
        category.setName(null);
        category.setDescription("Descripción válida");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("El nombre de la categoría es obligatorio.", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenNameIsEmpty() {
        CategoryDomain category = new CategoryDomain();
        category.setName(" ");
        category.setDescription("Descripción válida");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("El nombre de la categoría es obligatorio.", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenDescriptionIsNull() {
        CategoryDomain category = new CategoryDomain();
        category.setName("Categoría válida");
        category.setDescription(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("La descripción de la categoría es obligatoria.", exception.getMessage());
    }

    @Test
    void saveCategory_ShouldThrowException_WhenDescriptionIsEmpty() {
        CategoryDomain category = new CategoryDomain();
        category.setName("Categoría válida");
        category.setDescription(" ");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("La descripción de la categoría es obligatoria.", exception.getMessage());
    }
}

