package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne
    private Rate rate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Salary> salaries;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<InputOutput> inputOutputs;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Column(nullable = false)
    private Date accountExpireDate = new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 30 * 6);

    @Column(nullable = false)
    private Boolean accountLocked = false;

    @Column(nullable = false)
    private Date credentialsExpireDate = new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 30 * 3);

    @Column(nullable = false)
    private Boolean enabled = false;

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.getUsers().add(this);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.getUsers().remove(this);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpireDate.after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsExpireDate.after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
