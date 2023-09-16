package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.validator.IsValidEnum;

@Data
public class StatusRequest {
    @NotNull(message = "status must not be null")
    @NotBlank(message = "status must not be empty")
    @IsValidEnum(enumClazz = Status.class, message = "status must be any of NEW, IN_PROGRESS, COMPLETED")
    private String status;
}
