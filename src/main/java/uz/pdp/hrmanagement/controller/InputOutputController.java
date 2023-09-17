package uz.pdp.hrmanagement.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import uz.pdp.hrmanagement.dto.InputOutputDTO;
import uz.pdp.hrmanagement.response.Response;
import uz.pdp.hrmanagement.service.InputOutputService;

@RestController
@RequestMapping("/input-outputs")
public class InputOutputController {
    @Autowired
    private InputOutputService inputOutputService;

    @PostMapping
    public ResponseEntity<Response> input(Authentication authentication) {
        InputOutputDTO inputOutput = inputOutputService.input(authentication.getName());

        Response response = new Response(HttpStatus.CREATED.name(), inputOutput);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> output(@PathVariable UUID id) {
        InputOutputDTO inputOutput = inputOutputService.output(id);

        Response response = new Response(HttpStatus.OK.name(), inputOutput);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
