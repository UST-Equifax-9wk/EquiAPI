package com.revature.ecommerce;

//import com.revature.ecommerce.services.CartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableMethodSecurity
public class EcommerceApplication {

	public static void main(String[] args) {

		ApplicationContext iocContainer = SpringApplication.run(EcommerceApplication.class, args);
	}
}
