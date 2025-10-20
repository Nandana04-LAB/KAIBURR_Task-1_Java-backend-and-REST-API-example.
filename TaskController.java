package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskExecution;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Task> task = taskService.getTaskById(id);
            return task.map(List::of).orElse(List.of());
        }
        return taskService.getAllTasks();
    }

    @GetMapping("/search")
    public List<Task> findByName(@RequestParam String name) {
        return taskService.findTasksByName(name);
    }

    @PutMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        Task saved = taskService.saveTask(task);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/run")
    public ResponseEntity<TaskExecution> runTask(@PathVariable String id) {
        try {
            TaskExecution execution = taskService.runTask(id);
            return ResponseEntity.ok(execution);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
