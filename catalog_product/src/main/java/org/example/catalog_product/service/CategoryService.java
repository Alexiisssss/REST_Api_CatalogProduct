package org.example.catalog_product.service;

import org.example.catalog_product.dto.CategoryDTO;
import org.example.catalog_product.dto.ProductDTO;
import org.example.catalog_product.entity.Category;
import org.example.catalog_product.exception.CategoryNotFoundException;
import org.example.catalog_product.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(CategoryDTO categoryDTO) {
        try {
            Category category = new Category();
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
            logger.info("Category saved successfully: {}", categoryDTO.getName());
        } catch (Exception e) {
            logger.error("Error saving category: {}", categoryDTO.getName(), e);
            throw new RuntimeException("Failed to save category", e);
        }
    }


    public List<CategoryDTO> findAll() {
        try {
            return categoryRepository.findAll().stream()
                    .map(category -> new CategoryDTO(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getProducts().stream()
                                    .map(product -> new ProductDTO(
                                            product.getId(),
                                            product.getName(),
                                            product.getPrice()))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching categories", e);
            throw new RuntimeException("Failed to fetch categories", e);
        }
    }


    public CategoryDTO findById(Long id) {
        try {
            return categoryRepository.findById(id)
                    .map(category -> new CategoryDTO(
                            category.getId(),
                            category.getName(),
                            category.getDescription(),
                            category.getProducts().stream()
                                    .map(product -> new ProductDTO(
                                            product.getId(),
                                            product.getName(),
                                            product.getPrice()))
                                    .collect(Collectors.toList())))
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        } catch (CategoryNotFoundException e) {
            logger.error("Error fetching category with id: {}", id, e);
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching category with id: {}", id, e);
            throw new RuntimeException("Failed to fetch category", e);
        }
    }


    @Transactional
    public boolean update(Long id, CategoryDTO categoryDTO) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                category.setName(categoryDTO.getName());
                category.setDescription(categoryDTO.getDescription());
                categoryRepository.save(category);
                logger.info("Category updated successfully: {}", categoryDTO.getName());
                return true;
            } else {
                logger.warn("Category not found with id: {}", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error updating category with id: {}", id, e);
            throw new RuntimeException("Failed to update category", e);
        }
    }


    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                categoryRepository.deleteById(id);
                logger.info("Category deleted successfully with id: {}", id);
                return true;
            } else {
                logger.warn("Category not found with id: {}", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error deleting category with id: {}", id, e);
            throw new RuntimeException("Failed to delete category", e);
        }
    }
}
