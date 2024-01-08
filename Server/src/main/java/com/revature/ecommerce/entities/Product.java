package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer productId;

    private String type;


    private Integer quantity;

    private Integer threshold;

    private String name;

    private String description;
 
    @Column(name = "retail_price")
    private Double retailPrice;
    @Column(name = "discounted_price")
    private Double discountedPrice;
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
