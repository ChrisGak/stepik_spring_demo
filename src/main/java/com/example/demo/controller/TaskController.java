package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping()
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping()
    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskRepository.findById(id).orElse(null);
    }

    @PatchMapping("/{id}")
    public void markAsDone(@PathVariable Long id,
                           @RequestBody Task task) {
        if (task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }

    @PatchMapping("/{id}:mark-as-done")
    public void markAsDone(@PathVariable Long id) {
        taskRepository.markAsDone(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                      @RequestBody Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
