package com.revature.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

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
        SellerSummary getSeller();

        interface SellerSummary {
            Integer getId();
            String getFirstName();
            String getLastName();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_type")
    private String productType;
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews;

//    @OneToOne(mappedBy = "product")
//    private Cart cart;
  
    public Product() {
    }

    public Product(String productType, Integer inventory, Integer threshold, String name, String description) {
        this.productType = productType;
        this.inventory = inventory;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
    }

    public Product(Integer productId, String productType, Integer inventory, Integer threshold, String name, String description) {
        this.productId = productId;
        this.productType = productType;
        this.inventory = inventory;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
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

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productType, product.productType)
                && Objects.equals(inventory, product.inventory) && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && Objects.equals(retailPrice, product.retailPrice)
                && Objects.equals(discountedPrice, product.discountedPrice) && Objects.equals(threshold, product.threshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productType, inventory, name, description, retailPrice, discountedPrice, threshold);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productType='" + productType + '\'' +
                ", inventory=" + inventory +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", retailPrice=" + retailPrice +
                ", discountedPrice=" + discountedPrice +
                ", threshold=" + threshold +
                '}';
    }
}
