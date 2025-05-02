package com.task.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.inventory.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // No need to add anything — JpaRepository gives you CRUD out of the box!
}

