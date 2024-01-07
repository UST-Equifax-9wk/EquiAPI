package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Inventory;
import com.revature.ecommerce.repositories.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class InventoryService {
    private InventoryRepository inventoryRepository;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory findProductByProductId(String productId){
        Inventory product = inventoryRepository.findProductByProductId(productId);
        return product;
    }

    public Inventory addItem(Inventory product){
        return inventoryRepository.save(product);
    }
}
