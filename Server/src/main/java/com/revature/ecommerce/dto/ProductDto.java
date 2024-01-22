package com.revature.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    public Integer productId;
    public String productType;
    public Integer inventory;
    public String name;
    public String description;
    public Double retailPrice;
    public Double discountedPrice;
    public Integer threshold;
}
