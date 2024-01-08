package com.revature.ecommerce.entities;

import jakarta.persistence.*;

@Entity(name = "products")
public class Product {
    private Integer productId;
    private String type;
    private Integer quantity;
    private String name;
    private String description;

    public Product() {
    }

    public Product(String type, Integer quantity, String name, String description) {
        this.type = type;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
    }

    public Product(Integer productId, String type, Integer quantity, String name, String description) {
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
