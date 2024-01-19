package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "products")
@Data
@AllArgsConstructor
public class Product {
    /*
        These fields are all we need to render the product card
        It is used as a response body for GET /products
        Reference: https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html#projections.interfaces
     */
    public interface ProductCard {
        Integer getProductId();
        String getName();
        Double getRetailPrice();
        Double getDiscountedPrice();
        String getDescription();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_type")
    private String productType;

    @Column
    private String name;

    @Column(length = 2000)
    private String description;
    @Column(name = "retail_price")
    private Double retailPrice;
    @Column(name = "discounted_price")
    private Double discountedPrice;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private Set<Review> reviews;

    @JsonManagedReference(value = "productReviewReference")
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new HashSet<Review>();
  
    public Product() {
    }

    public Product(String productType, Integer threshold, String name, String description, Set<Review> reviews) {
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.reviews = reviews;
    }

    public Product(Integer productId, String productType, Integer threshold, String name,
                   String description, Set<Review> reviews) {
        this.productId = productId;
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.reviews = reviews;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productType, product.productType)
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && Objects.equals(retailPrice, product.retailPrice)
                && Objects.equals(discountedPrice, product.discountedPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productType, name, description, retailPrice, discountedPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productType='" + productType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", retailPrice=" + retailPrice +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
