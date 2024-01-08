package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository <Auth, String>{
//    @Query(nativeQuery = true, value = "SELECT password WHERE email = ?1")
    String findPasswordByEmail(String email);
}
