package uz.pdp.hrmanagement.service;

import org.springframework.security.core.Authentication;
import uz.pdp.hrmanagement.entity.enums.Role;

public interface AuthService {
    Authentication getAuthentication();
    boolean checkAuthority(Role name);
}
