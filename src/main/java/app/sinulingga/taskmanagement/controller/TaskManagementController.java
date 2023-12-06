package app.sinulingga.taskmanagement.controller;

import app.sinulingga.taskmanagement.dto.AddTaskManagementRequest;
import app.sinulingga.taskmanagement.dto.ResponseBasic;
import app.sinulingga.taskmanagement.dto.TaskManagementResponse;
import app.sinulingga.taskmanagement.dto.UpdateTaskStatusRequest;
import app.sinulingga.taskmanagement.exception.BadRequestException;
import app.sinulingga.taskmanagement.exception.DataNotFoundException;
import app.sinulingga.taskmanagement.service.TaskManagementService;
import app.sinulingga.taskmanagement.utility.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/task-management/api/v1")
public class TaskManagementController {

    @Autowired
    private TaskManagementService taskManagementService;

    @PostMapping(value = "/tasks")
    public ResponseEntity<ResponseBasic> add(@RequestBody AddTaskManagementRequest request) {
        try {
            taskManagementService.add(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/update-task-status-complete")
    public ResponseEntity<ResponseBasic> updateTaskStatusComplete(@RequestBody UpdateTaskStatusRequest request) {
        try {
            taskManagementService.updateTaskStatus(request);
            return ResponseHelper.createResponse(HttpStatus.OK, "Success");
        } catch (BadRequestException e) {
            return ResponseHelper.createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<ResponseBasic> getAll() {
        try {
            Set<TaskManagementResponse> response = taskManagementService.getAll();
            return ResponseHelper.createResponse(HttpStatus.OK, "Success", response);
        } catch (DataNotFoundException e) {
            return ResponseHelper.createResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
