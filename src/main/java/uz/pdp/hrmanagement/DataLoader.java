package uz.pdp.hrmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("director");
        user.setLastName("last");
        user.setEmail("director@mail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        Authority authority = new Authority();
        authority.setName(Role.DIRECTOR);
        user.setAuthorities(Set.of(authority));
        userRepository.save(user);
    }
}
