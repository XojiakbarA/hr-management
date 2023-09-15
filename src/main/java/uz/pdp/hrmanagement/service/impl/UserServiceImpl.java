package uz.pdp.hrmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email = " + username + " not found")
        );
    }
}
