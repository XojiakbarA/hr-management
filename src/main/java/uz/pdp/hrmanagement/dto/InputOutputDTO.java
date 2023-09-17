package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class InputOutputDTO {
    private UUID id;
    private UserDTO user;
    private Date visitedAt;
    private Date leftAt;
}
