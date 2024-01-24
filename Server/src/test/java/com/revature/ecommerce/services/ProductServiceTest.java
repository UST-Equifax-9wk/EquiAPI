// package com.revature.ecommerce.services;

// import com.revature.ecommerce.dto.ProductDto;
// import com.revature.ecommerce.entities.Product;
// import com.revature.ecommerce.mappers.ProductMapper;
// import com.revature.ecommerce.repositories.ProductRepository;
// import org.checkerframework.checker.units.qual.A;
// import org.hibernate.ObjectNotFoundException;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

// import java.util.List;
// import java.util.Optional;


// @SpringBootTest
// public class ProductServiceTest {
//     @Autowired
//     ProductService productService;
//     @MockBean
//     ProductRepository productRepository;
//     @MockBean
//     ProductMapper productMapper;

//     @Test
//     void shouldReturnListOfProductCards() throws Exception {
//         Integer pageNum = 0;
//         Integer pageSize = 5;
//         Pageable paging = PageRequest.of(pageNum, pageSize);
//         Product.ProductCard productCard = new Product.ProductCard() {
//             @Override
//             public Integer getProductId() {
//                 return 1;
//             }

//             @Override
//             public String getName() {
//                 return "Test";
//             }

//             @Override
//             public Double getRetailPrice() {
//                 return 20.00;
//             }

//             @Override
//             public Double getDiscountedPrice() {
//                 return 10.00;
//             }

//             @Override
//             public String getDescription() {
//                 return "test";
//             }
//         };
//         List<Product.ProductCard> productCardList = List.of(productCard);
//         Page<Product.ProductCard> productCardPage = new PageImpl<>(productCardList, paging, 1);
//         Mockito.when(productRepository.findAllProjectedBy(paging)).thenReturn(productCardPage);
//         List<Product.ProductCard> result = productService.getAllProducts(pageNum, pageSize);
//         Assertions.assertEquals(result, List.of(productCard));
//     }

//     @Test
//     void shouldAddNewProductAndReturnIt() throws Exception {
//         Product product = new Product();
//         product.setName("Test");
//         Product saved = new Product();
//         product.setProductId(1);
//         product.setName("Test");
//         Mockito.when(productRepository.save(product)).thenReturn(saved);
//         Product result = productService.addNewProduct(product);
//         Assertions.assertEquals(result, saved);
//     }

//     @Test
//     void shouldReturnProductWhenFindByItsId() throws Exception {
//         Product p = new Product();
//         p.setProductId(1);
//         p.setName("Test");
//         Mockito.when(productRepository.findById(p.getProductId())).thenReturn(Optional.of(p));
//         Product result = productService.findById(p.getProductId());
//         Assertions.assertEquals(result, p);
//     }

//     @Test
//     void shouldThrowObjectNotFoundExceptionIfObjectDoesNotExist() throws Exception {
//         Product p = new Product();
//         p.setProductId(1);
//         p.setName("Test");
//         Mockito.when(productRepository.findById(p.getProductId())).thenReturn(Optional.empty());
//         Assertions.assertThrows(ObjectNotFoundException.class, () -> productService.findById(p.getProductId()));
//     }

//     @Test
//     void shouldUpdateTheProduct() throws Exception {
//         Product product = new Product();
//         product.setProductId(1);
//         product.setName("Test");
//         ProductDto productDto = new ProductDto();
//         productDto.setProductId(1);
//         productDto.setName("Test2");
//         Product updated = new Product();
//         updated.setProductId(1);
//         updated.setName("Test");
//         Mockito.when(productRepository.findById(productDto.getProductId())).thenReturn(Optional.of(product));
//         Mockito.when(productRepository.save(product)).thenReturn(updated);
//         Product result = productService.updateProduct(productDto);
//         Mockito.verify(productMapper, Mockito.times(1)).updateProductFromDto(productDto, product);
//         Assertions.assertEquals(result, updated);
//     }

//     @Test
//     void shouldDeleteProduct() throws Exception {
//         String productId = "1";
//         Product product = new Product();
//         Mockito.when(productRepository.findById(Integer.valueOf(productId))).thenReturn(Optional.of(product));
//         productService.deleteProduct(productId);
//         Mockito.verify(productRepository, Mockito.times(1)).delete(product);
//     }
// }
