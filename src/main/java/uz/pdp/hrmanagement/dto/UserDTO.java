package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Grade;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Grade rateGrade;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
