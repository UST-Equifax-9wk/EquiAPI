package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer>{
    User findUserByEmail(String email);
}
