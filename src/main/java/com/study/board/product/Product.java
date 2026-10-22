package com.study.board.product;

import com.study.board.category.Category;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String productCode;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    protected Product() {
    }

    public Product(String name, String productCode, Category category) {
        this.name = name;
        this.productCode = productCode;
        this.category = category;
    }

    public void update(String name, String productCode, Category category) {
        this.name = name;
        this.productCode = productCode;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode;
    }

    public Category getCategory() {
        return category;
    }
}
