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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;  //fix this

    @Column
    private String productType;
    @Column  //Setting default values ???
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

    @OneToOne
    private Cart cart;
  
    public Product() {
    }

    public Product(String productType, Integer inventory, Integer threshold, String name, String description) {
        this.productType = productType;
        this.inventory = inventory;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
    }

    public Product(Integer id, String productType, Integer inventory, Integer threshold, String name, String description) {
        this.id = id;
        this.productType = productType;
        this.inventory = inventory;
        this.threshold = threshold;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, product.id) && Objects.equals(productType, product.productType)
                && Objects.equals(inventory, product.inventory) && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && Objects.equals(retailPrice, product.retailPrice)
                && Objects.equals(discountedPrice, product.discountedPrice) && Objects.equals(threshold, product.threshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productType, inventory, name, description, retailPrice, discountedPrice, threshold);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
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
