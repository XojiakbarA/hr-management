package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.enums.Status;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUsers_Email(String email);
    List<Task> findAllByStatusAndUsers_Id(Status status, UUID userId);
    List<Task> findAllByDeadlineAfterAndStatusNot(Date date, Status status);
    Optional<Task> findByIdAndUsers_Email(UUID id, String email);
}
