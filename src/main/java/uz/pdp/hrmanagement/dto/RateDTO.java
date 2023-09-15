package uz.pdp.hrmanagement.dto;

import lombok.Builder;
import lombok.Data;
import uz.pdp.hrmanagement.entity.enums.Grade;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class RateDTO {
    private UUID id;
    private Grade grade;
    private Integer value;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
