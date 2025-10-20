package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskExecution;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findTasksByName(String name) {
        return taskRepository.findByNameContainingIgnoreCase(name);
    }

    public TaskExecution runTask(String id) throws Exception {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty()) {
            throw new IllegalArgumentException("Task not found");
        }

        Task task = taskOpt.get();
        Process process = Runtime.getRuntime().exec(task.getCommand());

        BufferedReader outputReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));

        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        String line;

        while ((line = outputReader.readLine()) != null) {
            output.append(line).append("\n");
        }
        while ((line = errorReader.readLine()) != null) {
            error.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        return new TaskExecution(id, output.toString(), error.toString(), exitCode);
    }
}
