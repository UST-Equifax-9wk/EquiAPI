package com.revature.ecommerce.controllers;

import com.revature.ecommerce.entities.Inventory;
import com.revature.ecommerce.services.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@CrossOrigin
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add-to-inventory")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Inventory addToInventory(@RequestBody Inventory inventory, HttpServletResponse response){
        System.out.println(inventory);
        return inventoryService.addItem(inventory);
    }
}
