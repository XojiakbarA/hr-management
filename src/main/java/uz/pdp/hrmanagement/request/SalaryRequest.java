package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.validator.IsMonth;
import uz.pdp.hrmanagement.validator.IsYear;

@Data
public class SalaryRequest {
    @NotNull(message = "year must not be null", groups = OnCreate.class)
    @Min(value = 2000, message = "year must be between 2000 and 2200")
    @Max(value = 2200, message = "year must be between 2000 and 2200")
    @IsYear
    private Integer year;

    @NotNull(message = "month must not be null", groups = OnCreate.class)
    @Min(value = 1, message = "month must be between 1 and 12")
    @Max(value = 12, message = "month must be between 1 and 12")
    @IsMonth
    private Integer month;
}
