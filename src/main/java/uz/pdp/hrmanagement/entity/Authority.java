package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.hrmanagement.entity.enums.Role;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
