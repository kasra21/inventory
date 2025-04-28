package com.task.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.inventory.entity.Item;
import com.task.inventory.repo.ItemRepository;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getAllItems() {
    	System.out.println("Getting all items");
        return itemRepository.findAll();
    }
    
    @PostMapping("/api/items")
    public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
    	System.out.println("Received new item: " + newItem);
        Item createdItem = itemRepository.save(newItem);
        return ResponseEntity.ok(createdItem);
    }
}
