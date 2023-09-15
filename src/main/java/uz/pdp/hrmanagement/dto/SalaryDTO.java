package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class SalaryDTO {
    private UUID id;
    private Integer value;
    private UserDTO user;
    private Date takenAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
