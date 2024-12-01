package org.example.catalog_product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductFormController {

    @GetMapping("/product-form")
    public String showProductForm() {
        return "productForm";
    }
}
