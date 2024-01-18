package com.revature.ecommerce.mappers;

import com.revature.ecommerce.dto.CustomerDto;
import com.revature.ecommerce.entities.Customer;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);
}
