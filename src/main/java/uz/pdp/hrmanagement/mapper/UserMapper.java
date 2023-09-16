package uz.pdp.hrmanagement.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.UserDTO;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.User;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDTO mapToUserDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .authorities(user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()))
                .rateGrade(user.getRate() != null ? user.getRate().getGrade() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
