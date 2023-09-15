package uz.pdp.hrmanagement.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.UserDTO;
import uz.pdp.hrmanagement.entity.User;

@Component
public class UserMapper {
    public UserDTO mapToUserDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .rateGrade(user.getRate().getGrade())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
