package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class SalaryDTO {
    private UUID id;
    private Integer value;
    private UserDTO user;
    private Date takenAt;
}
