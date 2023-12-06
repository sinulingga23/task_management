package app.sinulingga.taskmanagement.dto;

public class UpdateTaskStatusRequest {
    private String taskManagementId;

    public UpdateTaskStatusRequest() {

    }

    public String getTaskManagementId() {
        return taskManagementId;
    }

    public void setTaskManagementId(String taskManagementId) {
        this.taskManagementId = taskManagementId;
    }

    @Override
    public String toString() {
        return "UpdateTaskStatusRequest{" +
                "taskManagementId='" + taskManagementId + '\'' +
                '}';
    }
}
