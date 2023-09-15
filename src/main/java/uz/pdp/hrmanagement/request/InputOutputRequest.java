package uz.pdp.hrmanagement.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import uz.pdp.hrmanagement.marker.OnCreate;
import uz.pdp.hrmanagement.marker.OnUpdate;

import java.util.Date;

@Data
public class InputOutputRequest {
    @NotNull(message = "visitedAt must not be null", groups = OnCreate.class)
    @Null(message = "visitedAt must be null", groups = OnUpdate.class)
    private Date visitedAt;

    @NotNull(message = "leftAt must not be null", groups = OnUpdate.class)
    @Null(message = "leftAt must be null", groups = OnCreate.class)
    private Date leftAt;
}
