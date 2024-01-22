package com.revature.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerLoginDto {
    String email;
    String password;
}
