package uz.pdp.hrmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.request.TaskRequest;
import uz.pdp.hrmanagement.response.Response;
import uz.pdp.hrmanagement.service.TaskService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/tasks")
@PreAuthorize("hasAnyAuthority(T(uz.pdp.hrmanagement.entity.enums.Role).DIRECTOR.name(), T(uz.pdp.hrmanagement.entity.enums.Role).MANAGER.name())")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Response> getAllTasks(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<TaskDTO> tasks = taskService.getAll(PageRequest.of(page, size));

        Response response = new Response(HttpStatus.OK.name(), tasks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/deadline-expired")
    public ResponseEntity<Response> getAllTasksDeadlineExpired() {
        List<TaskDTO> tasks = taskService.getAllByDeadlineAfterAndStatusNot(new Date(), Status.COMPLETED);

        Response response = new Response(HttpStatus.OK.name(), tasks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getTaskById(@PathVariable UUID id) {
        TaskDTO task = taskService.getById(id);

        Response response = new Response(HttpStatus.OK.name(), task);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> createTask(@Valid @RequestBody TaskRequest request) {
        TaskDTO task = taskService.create(request);

        Response response = new Response(HttpStatus.CREATED.name(), task);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTask(@Valid @RequestBody TaskRequest request, @PathVariable UUID id) {
        TaskDTO task = taskService.update(request, id);

        Response response = new Response(HttpStatus.OK.name(), task);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTask(@PathVariable UUID id) {
        taskService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{taskId}/users/{userId}")
    public ResponseEntity<Response> addUserToTask(@PathVariable UUID taskId, @PathVariable UUID userId) {
        TaskDTO task = taskService.addUser(taskId, userId);

        Response response = new Response(HttpStatus.OK.name(), task);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}/users/{userId}")
    public ResponseEntity<Response> removeUserFromTask(@PathVariable UUID taskId, @PathVariable UUID userId) {
        TaskDTO task = taskService.removeUser(taskId, userId);

        Response response = new Response(HttpStatus.OK.name(), task);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
