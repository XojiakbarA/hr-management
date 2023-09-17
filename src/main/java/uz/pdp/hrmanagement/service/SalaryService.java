package uz.pdp.hrmanagement.service;

import java.time.Month;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import uz.pdp.hrmanagement.dto.SalaryDTO;
import uz.pdp.hrmanagement.entity.Salary;
import uz.pdp.hrmanagement.request.SalaryRequest;

public interface SalaryService {
    Page<SalaryDTO> getAllByMonth(Pageable pageable, Month month);
    Page<SalaryDTO> getAllByUserId(Pageable pageable, UUID userId);
    SalaryDTO create(SalaryRequest request, UUID userId);
    Salary save(Salary salary);
}
