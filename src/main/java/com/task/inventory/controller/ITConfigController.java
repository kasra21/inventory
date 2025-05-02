package com.task.inventory.controller;

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

import com.task.inventory.entity.ITConfig;
import com.task.inventory.repo.ITConfigRepository;

import java.time.LocalDate;
import java.util.List;

//a bit of a different implementation this time
@RestController
@RequestMapping("/api/it-config")
public class ITConfigController {

    @Autowired
    private ITConfigRepository itConfigRepository;

    @GetMapping
    public List<ITConfig> getAllConfigs() {
        return itConfigRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ITConfig> getConfigById(@PathVariable Long id) {
        return itConfigRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ITConfig> createConfig(@RequestBody ITConfig config) {
    	config.setUpdated(LocalDate.now());
    	config.setCreated(LocalDate.now());
        ITConfig saved = itConfigRepository.save(config);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ITConfig> updateConfig(@PathVariable Long id, @RequestBody ITConfig updatedConfig) {
        return itConfigRepository.findById(id)
                .map(existing -> {
                	updatedConfig.setUpdated(LocalDate.now());
                    updatedConfig.setId(id); // preserve existing ID
                    return ResponseEntity.ok(itConfigRepository.save(updatedConfig));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfig(@PathVariable Long id) {
        if (!itConfigRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itConfigRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}