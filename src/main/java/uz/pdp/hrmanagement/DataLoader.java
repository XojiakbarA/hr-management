package uz.pdp.hrmanagement;

import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.*;
import uz.pdp.hrmanagement.entity.enums.Grade;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.service.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RateService rateService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createAuthorities();
        createRates();
        createDirector();
        createManager();
        createEmployee();
        createEmployees();
        createSalaries();
        createTask();
    }

    private void createTask() {
        Task task = new Task();
        task.setName("task1");
        task.setDescription("desc1 desc1 desc1 desc1");
        task.setDeadline(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        User givenUser = userService.findByEmail("manager@mail.com");
        task.setUser(givenUser);
        taskService.save(task);
    }

    private void createAuthorities() {
        for (Role role : Role.values()) {
            Authority authority = new Authority();
            authority.setName(role);
            authorityService.save(authority);
        }
    }
    private void createRates() {
        for (Grade grade : Grade.values()) {
            Rate rate = new Rate();
            rate.setGrade(grade);
            rateService.save(rate);
        }
    }
    private void createDirector() {
        User user = new User();
        user.setFirstName("director");
        user.setLastName("last");
        user.setEmail("director@mail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.addAuthority(authorityService.findByName(Role.DIRECTOR));
        user.setEnabled(true);
        user.setRate(rateService.findAll().get(1));
        user.setVerifyCode(UUID.randomUUID());
        userService.save(user);
    }
    private void createManager() {
        User user = new User();
        user.setFirstName("manager");
        user.setLastName("last");
        user.setEmail("manager@mail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.addAuthority(authorityService.findByName(Role.MANAGER));
        user.setEnabled(true);
        user.setRate(rateService.findAll().get(1));
        user.setVerifyCode(UUID.randomUUID());
        userService.save(user);
    }
    private void createEmployee() {
        User user = new User();
        user.setFirstName("zarina");
        user.setLastName("akramova");
        user.setEmail("zarinaakramova5@gmail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.addAuthority(authorityService.findByName(Role.EMPLOYEE));
        user.setEnabled(true);
        user.setRate(rateService.findAll().get(1));
        user.setVerifyCode(UUID.randomUUID());
        userService.save(user);
    }
    private void createEmployees() {
        for (int i = 1; i <= 4; i++) {
            User user = new User();
            user.setFirstName("employee" + i);
            user.setLastName("last" + i);
            user.setEmail("employee" + i + "@mail.com");
            user.setPassword(passwordEncoder.encode("12345"));
            user.addAuthority(authorityService.findByName(Role.EMPLOYEE));
            user.setEnabled(true);
            user.setRate(rateService.findAll().get(i - 1));
            user.setVerifyCode(UUID.randomUUID());
            userService.save(user);
        }
    }
    private void createSalaries() {
        for (int i = 1; i <= 4; i++) {
            Salary salary = new Salary();
            salary.setYear(Year.of(2023));
            salary.setMonth(Month.of(1));
            User user = userService.findAll().get(i - 1);
            salary.setUser(user);
            salary.setValue(user.getRate().getGrade().getValue());
            user.setVerifyCode(UUID.randomUUID());
            salaryService.save(salary);
        }
        for (int i = 2; i <= 4; i++) {
            Salary salary = new Salary();
            salary.setYear(Year.of(2023));
            salary.setMonth(Month.of(2));
            User user = userService.findAll().get(i - 1);
            salary.setUser(user);
            salary.setValue(user.getRate().getGrade().getValue());
            salaryService.save(salary);
        }
        for (int i = 3; i <= 4; i++) {
            Salary salary = new Salary();
            salary.setYear(Year.of(2022));
            salary.setMonth(Month.of(2));
            User user = userService.findAll().get(i - 1);
            salary.setUser(user);
            salary.setValue(user.getRate().getGrade().getValue());
            user.setVerifyCode(UUID.randomUUID());
            salaryService.save(salary);
        }
    }
}
