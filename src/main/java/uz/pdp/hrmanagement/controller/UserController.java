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

import uz.pdp.hrmanagement.dto.InputOutputDTO;
import uz.pdp.hrmanagement.dto.SalaryDTO;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.dto.UserDTO;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.request.SalaryRequest;
import uz.pdp.hrmanagement.request.UserRequest;
import uz.pdp.hrmanagement.response.Response;
import uz.pdp.hrmanagement.service.InputOutputService;
import uz.pdp.hrmanagement.service.SalaryService;
import uz.pdp.hrmanagement.service.TaskService;
import uz.pdp.hrmanagement.service.UserService;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority(T(uz.pdp.hrmanagement.entity.enums.Role).DIRECTOR.name(), T(uz.pdp.hrmanagement.entity.enums.Role).MANAGER.name())")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private InputOutputService inputOutputService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<Response> getAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<UserDTO> users = userService.getAll(PageRequest.of(page, size));

        Response response = new Response(HttpStatus.OK.name(), users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable UUID id) {
        UserDTO user = userService.getById(id);

        Response response = new Response(HttpStatus.OK.name(), user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserRequest request) {
        UserDTO user = userService.create(request);

        Response response = new Response(HttpStatus.CREATED.name(), user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserRequest request, @PathVariable UUID id) {
        UserDTO user = userService.update(request, id);

        Response response = new Response(HttpStatus.OK.name(), user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);

        Response response = new Response(HttpStatus.ACCEPTED.name());

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/salaries")
    public ResponseEntity<Response> getSalariesByUserId(@PathVariable UUID id, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<SalaryDTO> salaries = salaryService.getAllByUserId(PageRequest.of(page, size), id);

        Response response = new Response(HttpStatus.OK.name(), salaries);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping("/{id}/salaries")
    public ResponseEntity<Response> createSalaryToUser(@Valid @RequestBody SalaryRequest request, @PathVariable UUID id) {
        SalaryDTO salary = salaryService.create(request, id);

        Response response = new Response(HttpStatus.OK.name(), salary);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/input-outputs")
    public ResponseEntity<Response> getInputOutputsByUserId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @PathVariable UUID id) {
        Page<InputOutputDTO> inputOutputs = inputOutputService.getInputOutputsByUserId(PageRequest.of(page, size), id);

        Response response = new Response(HttpStatus.OK.name(), inputOutputs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks/completed")
    public ResponseEntity<Response> getCompletedTasksByUserId(@PathVariable UUID id) {
        List<TaskDTO> tasks = taskService.getAllByStatusAndUserId(Status.COMPLETED, id);

        Response response = new Response(HttpStatus.OK.name(), tasks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}/authorities/{authorityId}")
    public ResponseEntity<Response> addAuthorityToUser(@PathVariable UUID userId, @PathVariable UUID authorityId) {
        UserDTO user = userService.addAuthority(userId, authorityId);

        Response response = new Response(HttpStatus.OK.name(), user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/authorities/{authorityId}")
    public ResponseEntity<Response> removeAuthorityFromUser(@PathVariable UUID userId, @PathVariable UUID authorityId) {
        UserDTO user = userService.removeAuthority(userId, authorityId);

        Response response = new Response(HttpStatus.OK.name(), user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}/rates/{rateId}")
    public ResponseEntity<Response> setRateToUser(@PathVariable UUID userId, @PathVariable UUID rateId) {
        UserDTO user = userService.setRate(userId, rateId);

        Response response = new Response(HttpStatus.OK.name(), user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
