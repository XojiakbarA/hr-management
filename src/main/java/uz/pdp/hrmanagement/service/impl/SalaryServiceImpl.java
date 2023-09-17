package uz.pdp.hrmanagement.service.impl;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityExistsException;
import uz.pdp.hrmanagement.dto.SalaryDTO;
import uz.pdp.hrmanagement.entity.Salary;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.mapper.SalaryMapper;
import uz.pdp.hrmanagement.repository.SalaryRepository;
import uz.pdp.hrmanagement.request.SalaryRequest;
import uz.pdp.hrmanagement.service.SalaryService;
import uz.pdp.hrmanagement.service.UserService;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public Page<SalaryDTO> getAllByMonth(Pageable pageable, Month month) {
        return salaryRepository.findAllByMonth(pageable, month).map(s -> salaryMapper.mapToSalaryDTO(s));
    }

    @Override
    public Page<SalaryDTO> getAllByUserId(Pageable pageable, UUID userId) {
        return salaryRepository.findAllByUserId(pageable, userId).map(s -> salaryMapper.mapToSalaryDTO(s));
    } 

    @Override
    public SalaryDTO create(SalaryRequest request, UUID userId) {
        User user = userService.findById(userId);
        if (user.getRate() == null) {
            throw new EntityActionVetoException("At user with id = " + userId + " rate not set yet. Please, first set rate for this user", null);
        }
        if (salaryRepository.existsByUserIdAndYearAndMonth(userId, Year.of(request.getYear()), Month.of(request.getMonth()))) {
            throw new EntityExistsException("Salary with userId = " + userId + " and year = " + request.getYear() + " and month = " + request.getMonth() + " already exists");
        }
        Salary salary = new Salary();

        salary.setUser(user);
        salary.setValue(user.getRate().getGrade().getValue());
        if (request.getYear() != null) {
            salary.setYear(Year.of(request.getYear()));
        }
        if (request.getMonth() != null) {
            salary.setMonth(Month.of(request.getMonth()));
        }

        return salaryMapper.mapToSalaryDTO(save(salary));
    }
    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }
}
