package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "salaries")
public class Salary {
    @Id
    private UUID id;

    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Date takenAt = new Date();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
