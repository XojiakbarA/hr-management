package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Grade;
import uz.pdp.hrmanagement.validator.IsValidEnum;

@Data
public class RateRequest {
    @NotNull(message = "grade must not be null")
    @NotBlank(message = "grade must not be empty")
    @IsValidEnum(enumClazz = Grade.class)
    private Grade grade;
}
