package com.task.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.inventory.entity.Item;
import com.task.inventory.repo.ItemRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getAllItems() {
    	System.out.println("Getting all items");
        return itemRepository.findAll();
    }
    
    @GetMapping("/api/items/{id}")
    public ResponseEntity<Optional<Item>> findItemById(@PathVariable Long id) {
    	System.out.println("Finding item by id");
        return ResponseEntity.ok(itemRepository.findById(id));
    }
    
    @PostMapping("/api/items")
    public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
    	System.out.println("Received new item: " + newItem);
        Item createdItem = itemRepository.save(newItem);
        return ResponseEntity.ok(createdItem);
    }
    
    @PutMapping("/api/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        return itemRepository.findById(id)
            .map(item -> {
                item.setName(updatedItem.getName());
                item.setQuantity(updatedItem.getQuantity());
                item.setPrice(updatedItem.getPrice());
                return ResponseEntity.ok(itemRepository.save(item));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/api/items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
    	return itemRepository.findById(id)
                .map(item -> {
                	itemRepository.delete(item);
                    return ResponseEntity.noContent().build();
                    
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
