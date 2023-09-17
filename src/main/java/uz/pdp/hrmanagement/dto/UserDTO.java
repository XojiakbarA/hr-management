package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Grade;
import uz.pdp.hrmanagement.entity.enums.Role;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> authorities;
    private Grade rateGrade;
}
