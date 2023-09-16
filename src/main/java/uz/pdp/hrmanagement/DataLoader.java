package uz.pdp.hrmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.Rate;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Grade;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.service.AuthorityService;
import uz.pdp.hrmanagement.service.RateService;
import uz.pdp.hrmanagement.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RateService rateService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createAuthorities();
        createRates();
        createDirector();
        createManager();
        createEmployee();
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
        userService.save(user);
    }
    private void createEmployee() {
        User user = new User();
        user.setFirstName("employee");
        user.setLastName("last");
        user.setEmail("employee@mail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.addAuthority(authorityService.findByName(Role.EMPLOYEE));
        user.setEnabled(true);
        userService.save(user);
    }
}
