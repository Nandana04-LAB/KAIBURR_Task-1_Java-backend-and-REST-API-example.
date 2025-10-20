package com.taskmanager.model;

public class TaskExecution {

    private String taskId;
    private String output;
    private String error;
    private int exitCode;

    public TaskExecution(String taskId, String output, String error, int exitCode) {
        this.taskId = taskId;
        this.output = output;
        this.error = error;
        this.exitCode = exitCode;
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public int getExitCode() { return exitCode; }
    public void setExitCode(int exitCode) { this.exitCode = exitCode; }
}
