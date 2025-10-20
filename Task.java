package com.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

    @Id
    private String id;
    private String name;
    private String description;
    private String command;

    public Task() {}

    public Task(String id, String name, String description, String command) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.command = command;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }
}
