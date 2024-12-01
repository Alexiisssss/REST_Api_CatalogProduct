package org.example.catalog_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.catalog_product.dto.CategoryDTO;
import org.example.catalog_product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories API", description = "API для управления категориями")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Создание категории", description = "Добавляет новую категорию.")
    public String createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return "Category created!";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление категории", description = "Удаляет категорию по идентификатору.")
    public String deleteCategory(@PathVariable Long id) {
        if (categoryService.delete(id)) {
            return "Category deleted!";
        } else {
            return "Category not found!";
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление категории", description = "Обновляет данные категории по идентификатору.")
    public String editCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        if (categoryService.update(id, categoryDTO)) {
            return "Category updated!";
        } else {
            return "Category not found!";
        }
    }

    @GetMapping
    @Operation(summary = "Получение всех категорий", description = "Возвращает список всех категорий.")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll();
    }
}
