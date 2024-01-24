// package com.revature.ecommerce.repositories;

<<<<<<< HEAD
import com.revature.ecommerce.entities.Cart;
import com.revature.ecommerce.entities.Customer;
import com.revature.ecommerce.services.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
=======
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
>>>>>>> e63a820032906d3199f43c742bf1c881b289fd83

// @DataJpaTest
// public class CartRepositoryTest {

//     @Autowired
//     CartRepository cartRepository;

<<<<<<< HEAD
    @MockBean
    CustomerService customerService;
    @MockBean
    Customer customer;

    @Autowired
    TestEntityManager entityManager;
=======
//     @Autowired
//     TestEntityManager entityManager;
>>>>>>> e63a820032906d3199f43c742bf1c881b289fd83

    @BeforeAll

    @Test
    void test(){
        Customer customer= new Customer(1, "testEmail", "test", "test","password");
        customerService.saveCustomer(customer);
        Cart cart = new Cart(1, 1, customer, 20.0, "product");
    }

// }
