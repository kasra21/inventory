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

import com.task.inventory.entity.Task;
import com.task.inventory.repo.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/api/tasks")
    public List<Task> getAllTasks() {
    	System.out.println("Getting all tasks");
        return taskRepository.findAll();
    }
    
    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<Optional<Task>> findTaskById(@PathVariable Long id) {
    	System.out.println("Finding item by id");
        return ResponseEntity.ok(taskRepository.findById(id));
    }
    
    @PostMapping("/api/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
    	System.out.println("Received new item: " + newTask);
    	newTask.setCreated(LocalDate.now());
    	newTask.setUpdated(LocalDate.now());
    	Task createdTask = taskRepository.save(newTask);
        return ResponseEntity.ok(createdTask);
    }
    
    @PutMapping("/api/tasks/{id}")
    public ResponseEntity<Task> updateItem(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setPriority(updatedTask.getPriority());
                task.setStatus(updatedTask.getStatus());
                task.setUpdated(LocalDate.now());
                return ResponseEntity.ok(taskRepository.save(task));
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
    	return taskRepository.findById(id)
                .map(item -> {
                	taskRepository.delete(item);
                    return ResponseEntity.noContent().build();
                    
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
