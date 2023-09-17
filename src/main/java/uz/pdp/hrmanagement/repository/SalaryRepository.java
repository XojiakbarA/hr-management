package uz.pdp.hrmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hrmanagement.entity.Salary;

import java.time.Year;
import java.time.Month;
import java.util.UUID;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    Page<Salary> findAllByMonth(Pageable pageable, Month month);
    Page<Salary> findAllByUserId(Pageable pageable, UUID userId);
    boolean existsByUserIdAndYearAndMonth(UUID userId, Year year, Month month);
}
