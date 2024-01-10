package com.revature.ecommerce;

//import com.revature.ecommerce.services.CartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {

		ApplicationContext iocContainer = SpringApplication.run(EcommerceApplication.class, args);
	}
}
