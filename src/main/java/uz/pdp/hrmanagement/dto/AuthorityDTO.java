package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Role;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class AuthorityDTO {
    private UUID id;
    private Role name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
