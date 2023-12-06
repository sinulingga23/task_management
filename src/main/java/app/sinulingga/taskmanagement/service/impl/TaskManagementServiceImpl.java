package app.sinulingga.taskmanagement.service.impl;

import app.sinulingga.taskmanagement.definition.TaskStatus;
import app.sinulingga.taskmanagement.dto.AddTaskManagementRequest;
import app.sinulingga.taskmanagement.dto.TaskManagementResponse;
import app.sinulingga.taskmanagement.dto.UpdateTaskStatusRequest;
import app.sinulingga.taskmanagement.entity.TaskManagement;
import app.sinulingga.taskmanagement.exception.BadRequestException;
import app.sinulingga.taskmanagement.exception.DataNotFoundException;
import app.sinulingga.taskmanagement.repository.TaskManagementRepository;
import app.sinulingga.taskmanagement.service.TaskManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {
    private static final Logger log = LoggerFactory.getLogger(TaskManagementServiceImpl.class);

    @Autowired
    private TaskManagementRepository taskManagementRepository;

    @Override
    public void add(AddTaskManagementRequest request) throws BadRequestException {
        try {
            if (request.getName() == null || request.getName().trim().isEmpty())
                throw new BadRequestException("Name Empty");
            if (request.getDescription() == null || request.getDescription().trim().isEmpty())
                throw new BadRequestException("Description Empty");

            TaskManagement taskManagement = new TaskManagement();
            taskManagement.setId(UUID.randomUUID());
            taskManagement.setName(request.getName().trim());
            taskManagement.setDescription(request.getDescription().trim());
            taskManagement.setTaskStatus(TaskStatus.INCOMPLETE);
            taskManagement.setCreatedAt(LocalDateTime.now());
            taskManagement.setCreatedBy("System");

            taskManagementRepository.save(taskManagement);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Exception when try add task management: " + e.getMessage());

            throw new BadRequestException(e.getClass().getSimpleName());
        }
    }

    @Override
    public void updateTaskStatus(UpdateTaskStatusRequest request)
            throws BadRequestException, DataNotFoundException {
        try {
            if (request.getTaskManagementId() == null || request.getTaskManagementId().trim().isEmpty())
                throw new DataNotFoundException("Data Not Found");

            UUID taskManagementId = UUID.fromString(request.getTaskManagementId().trim());
            Optional<TaskManagement> optional = taskManagementRepository.findById(taskManagementId);
            if (optional.isEmpty())
                throw new DataNotFoundException("Data Not Found");

            TaskManagement taskManagement = optional.get();
            if (taskManagement.getTaskStatus() != null && taskManagement.getTaskStatus() == TaskStatus.COMPLETE)
                throw new BadRequestException("Task status already COMPLETE.");

            taskManagement.setTaskStatus(TaskStatus.COMPLETE);
            taskManagement.setUpdatedAt(LocalDateTime.now());
            taskManagement.setUpdatedBy("System");

            taskManagementRepository.save(taskManagement);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
            log.info("Exception when try update task status management: " + e.getMessage());

            throw new DataNotFoundException("Data Not Found");
        } catch (BadRequestException | DataNotFoundException e) {
            throw e;
        }
    }

    @Override
    public Set<TaskManagementResponse> getAll() throws DataNotFoundException {
        try {
            List<TaskManagement> taskManagementList = taskManagementRepository.findAll();
            if (taskManagementList.isEmpty())
                throw new DataNotFoundException("Data Not Found");

            Set<TaskManagementResponse> result = new HashSet<>();
            for (TaskManagement taskManagement : taskManagementList)
                result.add(new TaskManagementResponse(taskManagement));

            return result;
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Exception when try find all task management: " + e.getMessage());

            throw new DataNotFoundException(e.getClass().getSimpleName());
        }
    }
}
