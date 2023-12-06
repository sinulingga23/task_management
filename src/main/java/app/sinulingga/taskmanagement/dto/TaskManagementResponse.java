package app.sinulingga.taskmanagement.dto;

import app.sinulingga.taskmanagement.entity.TaskManagement;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id", "name", "description",
        "taskStatus", "createdAt", "createdBy",
        "updatedAt", "updatedBy"
})
public class TaskManagementResponse {
    private String id;
    private String name;
    private String description;
    private String taskStatus;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public TaskManagementResponse() {

    }

    public TaskManagementResponse(TaskManagement taskManagement) {
        this.id = taskManagement.getId().toString();
        this.name = taskManagement.getName();
        this.description = taskManagement.getDescription();
        if (taskManagement.getTaskStatus() != null) {
            this.taskStatus = taskManagement.getTaskStatus().toString();
        }
        this.setCreatedAt(taskManagement.getCreatedAt());
        this.setCreatedBy(taskManagement.getCreatedBy());
        this.setUpdatedAt(taskManagement.getUpdatedAt());
        this.setUpdatedBy(taskManagement.getUpdatedBy());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "TaskManagementResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
