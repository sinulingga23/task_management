package app.sinulingga.taskmanagement.service;

import app.sinulingga.taskmanagement.dto.AddTaskManagementRequest;
import app.sinulingga.taskmanagement.dto.TaskManagementResponse;
import app.sinulingga.taskmanagement.dto.UpdateTaskStatusRequest;
import app.sinulingga.taskmanagement.exception.BadRequestException;
import app.sinulingga.taskmanagement.exception.DataNotFoundException;

import java.util.Set;

public interface TaskManagementService {
    void add(AddTaskManagementRequest request)
            throws BadRequestException;
    void updateTaskStatus(UpdateTaskStatusRequest request)
            throws BadRequestException, DataNotFoundException;
    Set<TaskManagementResponse> getAll()
        throws DataNotFoundException;
}
