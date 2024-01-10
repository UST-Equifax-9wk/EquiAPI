package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{
}
