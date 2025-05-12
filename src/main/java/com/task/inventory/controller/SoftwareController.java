package com.task.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.inventory.entity.Software;
import com.task.inventory.repo.SoftwareRepository;

//a bit of a different implementation this time
@RestController
@RequestMapping("/api/software")
public class SoftwareController {

    @Autowired
    private SoftwareRepository softwareRepository;

    @GetMapping
    public List<Software> getAllSoftwares() {
        return softwareRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Software> getSoftwareById(@PathVariable Long id) {
        return softwareRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Software> createSoftware(@RequestBody Software software) {
        Software saved = softwareRepository.save(software);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Software> updateSoftware(@PathVariable Long id, @RequestBody Software updatedSoftware) {
        return softwareRepository.findById(id)
                .map(existing -> {
                	updatedSoftware.setId(id); // preserve existing ID
                    return ResponseEntity.ok(softwareRepository.save(updatedSoftware));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoftware(@PathVariable Long id) {
        if (!softwareRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        softwareRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}