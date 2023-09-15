package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.validator.IsValidEnum;

@Data
public class AuthorityRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be empty")
    @IsValidEnum(enumC = Role.class)
    private Role name;
}
