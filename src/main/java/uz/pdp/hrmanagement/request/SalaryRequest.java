package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.hrmanagement.marker.OnCreate;

import java.util.Date;
import java.util.UUID;

@Data
public class SalaryRequest {
    @NotNull(message = "userId must not be null", groups = OnCreate.class)
    @Positive(message = "userId must be a positive")
    private UUID userId;

    private Date takenAt;
}
