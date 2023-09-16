package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import uz.pdp.hrmanagement.marker.OnCreate;

@Data
public class UserRequest {
    @NotNull(message = "firstName must not be null", groups = OnCreate.class)
    @NotBlank(message = "firstName must not be empty")
    private String firstName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be empty")
    private String lastName;

    @NotNull(message = "email must not be null", groups = OnCreate.class)
    @NotBlank(message = "email must not be empty", groups = OnCreate.class)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;
}
