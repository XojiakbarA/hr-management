package uz.pdp.hrmanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.SalaryDTO;
import uz.pdp.hrmanagement.entity.Salary;

@Component
public class SalaryMapper {
    @Autowired
    private UserMapper userMapper;
    public SalaryDTO mapToSalaryDTO(Salary salary) {
        if (salary == null) return null;

        return SalaryDTO.builder()
                .id(salary.getId())
                .value(salary.getValue())
                .user(userMapper.mapToUserDTO(salary.getUser()))
                .takenAt(salary.getTakenAt())
                .createdAt(salary.getCreatedAt())
                .updatedAt(salary.getUpdatedAt())
                .build();
    }
}
