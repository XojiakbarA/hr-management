package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Grade;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.marker.OnUpdate;
import uz.pdp.hrmanagement.validator.IsValidEnum;

import java.util.Set;

@Data
public class UserRequest {
    @NotNull(message = "firstName must not be null", groups = OnCreate.class)
    @NotBlank(message = "firstName must not be empty")
    private String firstName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be empty")
    private String lastName;

    @NotNull(message = "email must not be null", groups = OnCreate.class)
    @Null(message = "email must be null", groups = OnUpdate.class)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be empty")
    @Min(value = 5, message = "password must be min 5")
    private String password;

    @NotNull(message = "rateGrade must not be null", groups = OnCreate.class)
    @IsValidEnum(enumClazz = Grade.class)
    private Grade rateGrade;

    @NotNull(message = "authorities must not be null", groups = OnCreate.class)
    @NotEmpty(message = "authorities must not be empty")
    private Set<@IsValidEnum(enumClazz = Role.class) Role> authorities;
}
