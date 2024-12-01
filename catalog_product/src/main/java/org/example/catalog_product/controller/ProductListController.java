package org.example.catalog_product.controller;

import org.example.catalog_product.entity.Product;
import org.example.catalog_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product-list")
public class ProductListController {

    private final ProductService productService;

    @Autowired
    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }
}
