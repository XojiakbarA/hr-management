package uz.pdp.hrmanagement.controller;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.hrmanagement.dto.SalaryDTO;
import uz.pdp.hrmanagement.response.Response;
import uz.pdp.hrmanagement.service.SalaryService;
import uz.pdp.hrmanagement.validator.IsMonth;

@RestController
@RequestMapping("/salaries")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public ResponseEntity<Response> getAllSalariesByMonth(@RequestParam(required = false) @IsMonth Integer month, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<SalaryDTO> salaries = salaryService.getAllByMonth(PageRequest.of(page, size), month == null ? null : Month.of(month));

        Response response = new Response(HttpStatus.OK.name(), salaries);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
