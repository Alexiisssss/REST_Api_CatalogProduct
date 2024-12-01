package org.example.catalog_product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.catalog_product.dto.CategoryDTO;
import org.example.catalog_product.entity.Category;
import org.example.catalog_product.entity.Product;
import org.example.catalog_product.service.CategoryService;
import org.example.catalog_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@Tag(name = "Products API", description = "API для управления продуктами")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/new")
    @Operation(summary = "Показать форму добавления продукта", description = "Возвращает форму для создания нового продукта.")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "productAddForm";
    }

    @PostMapping("/new")
    @Operation(summary = "Добавить новый продукт", description = "Сохраняет новый продукт в базу данных.")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, @RequestParam Long categoryId) {
        if (bindingResult.hasErrors()) {
            return "productAddForm";
        }
        CategoryDTO categoryDTO = categoryService.findById(categoryId);
        if (categoryDTO != null) {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            product.setCategory(category);
        }
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/edit/{id}")
    @Operation(summary = "Обновить продукт", description = "Обновляет существующий продукт по идентификатору.")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product, @RequestParam Long categoryId) {
        Product existingProduct = productService.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());

            CategoryDTO categoryDTO = categoryService.findById(categoryId);
            if (categoryDTO != null) {
                Category category = new Category();
                category.setId(categoryDTO.getId());
                category.setName(categoryDTO.getName());
                category.setDescription(categoryDTO.getDescription());
                existingProduct.setCategory(category);
            }

            productService.save(existingProduct);
            return "redirect:/products";
        }
        return "redirect:/products";
    }

    @GetMapping
    @Operation(summary = "Список продуктов", description = "Возвращает список всех продуктов.")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productList";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Показать форму редактирования", description = "Возвращает форму для редактирования продукта.")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            product = new Product();
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "productEditForm";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Удалить продукт", description = "Удаляет продукт по идентификатору.")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.delete(id);
        }
        return "redirect:/products";
    }
}
