package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import uz.pdp.hrmanagement.entity.enums.Grade;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rates")
public class Rate {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "rate", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
