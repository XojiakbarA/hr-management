package uz.pdp.hrmanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.dto.TaskDTO;
import uz.pdp.hrmanagement.entity.Authority;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Role;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.event.AppEventPublisher;
import uz.pdp.hrmanagement.mapper.TaskMapper;
import uz.pdp.hrmanagement.repository.TaskRepository;
import uz.pdp.hrmanagement.request.StatusRequest;
import uz.pdp.hrmanagement.request.TaskRequest;
import uz.pdp.hrmanagement.service.AuthService;
import uz.pdp.hrmanagement.service.TaskService;
import uz.pdp.hrmanagement.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private AppEventPublisher appEventPublisher;

    @Override
    public Page<TaskDTO> getAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(t -> taskMapper.mapToTaskDTO(t));
    }

    @Override
    public List<TaskDTO> getAllByUserEmail(String email) {
        return taskRepository.findAllByUsers_Email(email).stream().map(t -> taskMapper.mapToTaskDTO(t)).toList();
    }

    @Override
    public List<TaskDTO> getAllByStatusAndUserId(Status status, UUID userId) {
        return taskRepository.findAllByStatusAndUsers_Id(status, userId).stream().map(t -> taskMapper.mapToTaskDTO(t)).toList();
    }

    @Override
    public TaskDTO getById(UUID id) {
        return taskMapper.mapToTaskDTO(findById(id));
    }

    @Override
    public TaskDTO create(TaskRequest request) {
        Task task = new Task();

        setUser(task);
        setName(task, request.getName());
        setDescription(task, request.getDescription());
        setDeadline(task, request.getDeadline());

        return taskMapper.mapToTaskDTO(save(task));
    }

    @Override
    public TaskDTO update(TaskRequest request, UUID id) {
        Task task = findById(id);

        setName(task, request.getName());
        setDescription(task, request.getDescription());
        setDeadline(task, request.getDeadline());

        return taskMapper.mapToTaskDTO(save(task));
    }

    @Override
    public TaskDTO addUser(UUID taskId, UUID userId) {
        Task task = findById(taskId);
        User user = userService.findById(userId);

        checkCurrentUserForAddRemove(user);

        task.addUser(user);
        Task savedTask = save(task);

        appEventPublisher.publishUserAddedToTask(user, savedTask);

        return taskMapper.mapToTaskDTO(savedTask);
    }

    @Override
    public TaskDTO removeUser(UUID taskId, UUID userId) {
        Task task = findById(taskId);
        User user = userService.findById(userId);

        checkCurrentUserForAddRemove(user);

        task.removeUser(user);
        return taskMapper.mapToTaskDTO(save(task));
    }

    @Override
    public TaskDTO setStatus(UUID id, String email, StatusRequest request) {
        Task task = taskRepository.findByIdAndUsers_Email(id, email).orElseThrow(
                () -> new EntityNotFoundException("Task with id = " + id + " and users_email = " + email + " not found")
        );
        setStatus(task, Status.valueOf(request.getStatus().toUpperCase()));

        Task savedTask = save(task);

        User userWhoSet = userService.findByEmail(email);

        appEventPublisher.publishUserSetTaskStatusEvent(savedTask, userWhoSet);

        return taskMapper.mapToTaskDTO(savedTask);
    }

    @Override
    public Task findById(UUID id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task with id = " + id + " not found")
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with id = " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    private void checkCurrentUserForAddRemove(User user) {
        boolean isManagerOrDirector = false;
        for (Authority authority : user.getAuthorities()) {
            if (authority.getName().equals(Role.DIRECTOR) || authority.getName().equals(Role.MANAGER)) {
                isManagerOrDirector = true;
                break;
            }
        }
        if (isManagerOrDirector) {
            boolean isDirector = authService.checkAuthority(Role.DIRECTOR);
            if (!isDirector) {
                throw new AccessDeniedException("You cannot add/remove a manager/director to task");
            }
        }
    }
    private void setUser(Task task) {
        User user = userService.findByEmail(authService.getAuthentication().getName());
        task.setUser(user);
    }
    private void setName(Task task, String name) {
        if (name != null && !name.isBlank()) {
            task.setName(name);
        }
    }
    private void setDescription(Task task, String description) {
        if (description != null && !description.isBlank()) {
            task.setDescription(description);
        }
    }
    private void setDeadline(Task task, Date deadline) {
        if (deadline != null) {
            task.setDeadline(deadline);
        }
    }
    private void setStatus(Task task, Status status) {
        if (status != null) {
            task.setStatus(status);
        }
    }
}
