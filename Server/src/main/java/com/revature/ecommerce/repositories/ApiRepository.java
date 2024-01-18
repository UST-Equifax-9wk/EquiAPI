package com.revature.ecommerce.repositories;

import com.revature.ecommerce.entities.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {
    Api findByapiId(UUID apiId);
}
