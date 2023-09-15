package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Status;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class TaskDTO {
    private UUID id;
    private String name;
    private String description;
    private Date deadline;
    private Status status;
    private Set<UserDTO> users;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
