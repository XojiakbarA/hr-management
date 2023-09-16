package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.validator.IsFuture;

import java.util.Date;

@Data
public class TaskRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be empty", groups = OnCreate.class)
    @Size(min = 3, max = 100, message = "name must not be less than 3 and not more than 100")
    private String name;

    @NotNull(message = "description must not be null", groups = OnCreate.class)
    @NotBlank(message = "description must not be empty", groups = OnCreate.class)
    @Size(min = 10, max = 200, message = "name must not be less than 10 and not more than 200")
    private String description;

    @NotNull(message = "deadline must not be null", groups = OnCreate.class)
    @IsFuture(message = "deadline must be future")
    private Date deadline;
}
