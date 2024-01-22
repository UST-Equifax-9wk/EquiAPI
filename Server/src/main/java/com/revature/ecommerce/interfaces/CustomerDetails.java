package com.revature.ecommerce.interfaces;

/*
    These fields are all we need to render the customer details
    It is used as a response body for GET /customers/{customerId}
    Reference: https://docs.spring.io/spring-data/jpa/reference/repositories/projections.html#projections.interfaces
 */
public interface CustomerDetails {
    Integer getCustomerId();
    String getEmail();
    String getFirstName();
    String getLastName();
}
