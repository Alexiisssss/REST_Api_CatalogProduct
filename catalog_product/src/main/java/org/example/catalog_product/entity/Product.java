package org.example.catalog_product.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private boolean active;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private LocalDate dateAdded = LocalDate.now();
}
