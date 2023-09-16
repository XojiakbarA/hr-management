package uz.pdp.hrmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.request.StatusRequest;
import uz.pdp.hrmanagement.response.Response;
import uz.pdp.hrmanagement.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/my/tasks")
public class MyTaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<Response> getAllTasksByUserEmail(Authentication authentication) {
        List<TaskDTO> tasks = taskService.getAllByUserEmail(authentication.getName());

        Response response = new Response(HttpStatus.OK.name(), tasks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> setTaskStatus(@Valid @RequestBody StatusRequest request, @PathVariable UUID id, Authentication authentication) {
        TaskDTO task = taskService.setStatus(id, authentication.getName(), request);

        Response response = new Response(HttpStatus.OK.name(), task);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
