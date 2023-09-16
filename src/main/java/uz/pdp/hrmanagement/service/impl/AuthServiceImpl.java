package uz.pdp.hrmanagement.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.service.AuthService;

@Component
public class AuthServiceImpl implements AuthService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public boolean checkAuthority(Role name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(name.name())) {
                return true;
            }
        }
        return false;
    }
}
