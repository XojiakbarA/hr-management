package uz.pdp.hrmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailAuthenticationException;
import uz.pdp.hrmanagement.dto.UserDTO;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.request.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<UserDTO> getAll(Pageable pageable);
    UserDTO getById(UUID id);
    UserDTO create(UserRequest request);
    UserDTO update(UserRequest request, UUID id);
    UserDTO addAuthority(UUID userId, UUID authorityId);
    UserDTO removeAuthority(UUID userId, UUID authorityId);
    UserDTO setRate(UUID userId, UUID rateId);
    List<User> findAll();
    User findById(UUID id);
    User findByEmail(String email);
    void deleteById(UUID id);
    User save(User user);
    void verifyEmail(String password, String code) throws MailAuthenticationException;
}
