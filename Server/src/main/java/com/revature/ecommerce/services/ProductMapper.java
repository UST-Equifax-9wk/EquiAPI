package com.revature.ecommerce.mappers;

import com.revature.ecommerce.dto.ProductDto;
import com.revature.ecommerce.entities.Product;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
