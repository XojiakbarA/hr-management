package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "salaries")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueUserIdAndYearAndMonth", columnNames = { "user_id", "year", "month" }) })
public class Salary {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Year year;

    @Column(nullable = false)
    private Month month;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
