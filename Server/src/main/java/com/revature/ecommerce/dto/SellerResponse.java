package com.revature.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}