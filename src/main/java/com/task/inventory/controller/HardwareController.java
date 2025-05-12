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

import com.task.inventory.entity.Hardware;
import com.task.inventory.repo.HardwareRepository;

//a bit of a different implementation this time
@RestController
@RequestMapping("/api/hardware")
public class HardwareController {

    @Autowired
    private HardwareRepository hardwareRepository;

    @GetMapping
    public List<Hardware> getAllHardwares() {
        return hardwareRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hardware> getHardwareById(@PathVariable Long id) {
        return hardwareRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hardware> createHardware(@RequestBody Hardware hardware) {
        Hardware saved = hardwareRepository.save(hardware);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hardware> updateHardware(@PathVariable Long id, @RequestBody Hardware updatedHardware) {
        return hardwareRepository.findById(id)
                .map(existing -> {
                	updatedHardware.setId(id); // preserve existing ID
                    return ResponseEntity.ok(hardwareRepository.save(updatedHardware));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHardware(@PathVariable Long id) {
        if (!hardwareRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hardwareRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}