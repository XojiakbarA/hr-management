package uz.pdp.hrmanagement.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.dto.UserDTO;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.Rate;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.mapper.UserMapper;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.request.UserRequest;
import uz.pdp.hrmanagement.service.AuthService;
import uz.pdp.hrmanagement.service.AuthorityService;
import uz.pdp.hrmanagement.service.RateService;
import uz.pdp.hrmanagement.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthService authService;
    @Autowired
    private RateService rateService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(u -> userMapper.mapToUserDTO(u));
    }

    @Override
    public UserDTO getById(UUID id) {
        return userMapper.mapToUserDTO(findById(id));
    }

    @Override
    public UserDTO create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("User with email = " + request.getEmail() + " already exists");
        }
        User user = new User();

        setFirstName(user, request.getFirstName());
        setLastName(user, request.getLastName());
        setEmail(user, request.getEmail());

        return userMapper.mapToUserDTO(save(user));
    }

    @Override
    public UserDTO update(UserRequest request, UUID id) {
        User user = findById(id);

        setFirstName(user, request.getFirstName());
        setLastName(user, request.getLastName());

        return userMapper.mapToUserDTO(save(user));
    }

    @Override
    public UserDTO addAuthority(UUID userId, UUID authorityId) {
        User user = findById(userId);
        Authority authority = authorityService.findById(authorityId);

        checkAuthorityForDirector(authority);

        user.addAuthority(authority);
        return userMapper.mapToUserDTO(save(user));
    }

    @Override
    public UserDTO removeAuthority(UUID userId, UUID authorityId) {
        User user = findById(userId);
        Authority authority = authorityService.findById(authorityId);

        checkAuthorityForDirector(authority);

        user.removeAuthority(authority);
        return userMapper.mapToUserDTO(save(user));
    }

    @Override
    public UserDTO setRate(UUID userId, UUID rateId) {
        User user = findById(userId);
        Rate rate = rateService.findById(rateId);
        user.setRate(rate);
        return userMapper.mapToUserDTO(save(user));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id = " + id + " not found")
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id = " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    private void checkAuthorityForDirector(Authority authority) {
        if (authority.getName().equals(Role.MANAGER) || authority.getName().equals(Role.DIRECTOR)) {
            boolean isDirector = authService.checkAuthority(Role.DIRECTOR);
            if (!isDirector) {
                throw new AccessDeniedException("You cannot appoint/dismiss a manager or director");
            }
        }
    }
    private void setFirstName(User user, String firstName) {
        if (firstName != null && !firstName.isBlank()) {
            user.setFirstName(firstName);
        }
    }
    private void setLastName(User user, String lastName) {
        if (lastName != null && !lastName.isBlank()) {
            user.setLastName(lastName);
        }
    }
    private void setEmail(User user, String email) {
        if (email != null && !email.isBlank()) {
            user.setEmail(email);
        }
    }
    private void setPassword(User user, String password) {
        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }
    }
}
