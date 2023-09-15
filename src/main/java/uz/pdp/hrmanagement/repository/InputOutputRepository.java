package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hrmanagement.entity.InputOutput;

import java.util.UUID;

@Repository
public interface InputOutputRepository extends JpaRepository<InputOutput, UUID> {
}