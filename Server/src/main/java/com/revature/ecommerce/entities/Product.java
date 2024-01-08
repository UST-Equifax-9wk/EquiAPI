package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Entity(name = "products")
@Data
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer productId;

    private String type;
    @Column(columnDefinition = "integer default 0")
    private Integer quantity = 0;
    @Column
    private Integer inventory;
    @Column
    private String name;
    @Column(length = 2000)
    private String description;
    @Column(name = "retail_price")
    private Double retailPrice;
    @Column(name = "discounted_price")
    private Double discountedPrice;
    @Column
    private Integer threshold;
    @ManyToOne
    @JsonBackReference(value = "seller-products")
    private Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews;
  
    public Product() {
    }

    public Product(String type, Integer quantity, Integer threshold, String name, String description) {
        this.type = type;
        this.quantity = quantity;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
    }

    public Product(Integer productId, String type, Integer quantity, Integer threshold, String name, String description) {
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
    }
}
