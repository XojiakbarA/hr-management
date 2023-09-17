package uz.pdp.hrmanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.entity.Task;

import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private UserMapper userMapper;
    public TaskDTO mapToTaskDTO(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .users(task.getUsers().stream().map(u -> userMapper.mapToUserDTO(u)).collect(Collectors.toSet()))
                .build();
    }
}
