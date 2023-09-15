package uz.pdp.hrmanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.InputOutputDTO;
import uz.pdp.hrmanagement.entity.InputOutput;

@Component
public class InputOutputMapper {
    @Autowired
    private UserMapper userMapper;
    public InputOutputDTO mapToInputOutputDTO(InputOutput inputOutput) {
        if (inputOutput == null) return null;

        return InputOutputDTO.builder()
                .id(inputOutput.getId())
                .user(userMapper.mapToUserDTO(inputOutput.getUser()))
                .visitedAt(inputOutput.getVisitedAt())
                .leftAt(inputOutput.getLeftAt())
                .createdAt(inputOutput.getCreatedAt())
                .updatedAt(inputOutput.getUpdatedAt())
                .build();
    }
}
