package com.revature.ecommerce;

//import com.revature.ecommerce.services.CartService;
import com.revature.ecommerce.controllers.CartController;
import com.revature.ecommerce.controllers.CustomerController;
import com.revature.ecommerce.controllers.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		ApplicationContext iocContainer = SpringApplication.run(EcommerceApplication.class, args);
		ProductController productController = (ProductController) iocContainer.getBean(ProductController.class);
		CustomerController customerController = (CustomerController) iocContainer.getBean(CustomerController.class);
		CartController cartController = (CartController) iocContainer.getBean(CartController.class);
	}
}
