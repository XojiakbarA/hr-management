package uz.pdp.hrmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.request.StatusRequest;
import uz.pdp.hrmanagement.request.TaskRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    Page<TaskDTO> getAll(Pageable pageable);
    List<TaskDTO> getAllByUserEmail(String email);
    List<TaskDTO> getAllByStatusAndUserId(Status status, UUID userId);
    List<TaskDTO> getAllByDeadlineAfterAndStatusNot(Date date, Status status);
    List<Task> findAllByDeadlineAfterAndStatusNot(Date date, Status status);
    TaskDTO getById(UUID id);
    TaskDTO create(TaskRequest request);
    TaskDTO update(TaskRequest request, UUID id);
    TaskDTO addUser(UUID taskId, UUID userId);
    TaskDTO removeUser(UUID taskId, UUID userId);
    TaskDTO setStatus(UUID id, String email, StatusRequest request);
    Task findById(UUID id);
    void deleteById(UUID id);
    Task save(Task task);
}
