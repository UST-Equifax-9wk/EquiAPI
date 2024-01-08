package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
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
}
