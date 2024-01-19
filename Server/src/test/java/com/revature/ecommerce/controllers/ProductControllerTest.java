package com.revature.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ecommerce.dto.ProductDto;
import com.revature.ecommerce.entities.Product;
import com.revature.ecommerce.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@WithMockUser(username = "test@test.com")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnTheProductWhenProductIsRequestedByItsId() throws Exception {
        int productId = 5;
        Product product = new Product();
        product.setProductId(5);
        when(productService.findById(5)).thenReturn(product);
        this.mockMvc.perform(get("/products/" + productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(5));
    }

    @Test
    void shouldReturnAllProductsWhenListIsRequested() throws Exception {
        Product.ProductCard productCard = new Product.ProductCard() {
            @Override
            public Integer getProductId() {
                return 1;
            }
            public String getName() {
                return "Test";
            }
            public Double getRetailPrice() {
                return 20.00;
            }
            public Double getDiscountedPrice() {
                return 10.00;
            }
        };
        List<Product.ProductCard> productCardList = List.of(productCard);
        Mockito.when(productService.getAllProducts(Mockito.anyInt(), Mockito.anyInt())).thenReturn(productCardList);
        this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].name").value("Test"))
                .andExpect(jsonPath("$[0].retailPrice").value(20.00))
                .andExpect(jsonPath("$[0].discountedPrice").value(10.00));
    }

    @Test
    void shouldUpdateTheProduct() throws Exception {
        Product p = new Product();
        p.setName("Test");
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1);
        productDto.setName("Hello");
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(productDto);
        when(productService.updateProduct(any(ProductDto.class))).thenReturn(p);
        this.mockMvc.perform(
                put("/products/" + "1")
                        .with(csrf())
                        .contentType("application/json")
                        .content(jsonString)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        this.mockMvc.perform(
                delete("/products/" + 1)
                        .with(csrf())
                        .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().isOk());
        Mockito.verify(productService, Mockito.times(1)).deleteProduct("1");
    }

    @Test
    void shouldAddNewProductAndReturnIt() throws Exception {
        Product p = new Product();
        p.setName("Test");
        ObjectMapper om = new ObjectMapper();
        String jsonString = om.writeValueAsString(p);
        Mockito.when(productService.addNewProduct(p)).thenReturn(p);
        this.mockMvc.perform(
                post("/products/add-product")
                        .with(csrf())
                        .contentType("application/json")
                        .content(jsonString)
        )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Test"));
    }
}
