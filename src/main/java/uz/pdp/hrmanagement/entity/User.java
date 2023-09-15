package uz.pdp.hrmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Rate rate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Salary> salaries;

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
    private Set<Authority> authorities;

    @Column(nullable = false)
    private Date accountExpireDate = new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 30 * 6);

    @Column(nullable = false)
    private Boolean accountLocked = false;

    @Column(nullable = false)
    private Date credentialsExpireDate = new Date(System.currentTimeMillis() + (long) 1000 * 60 * 60 * 24 * 30 * 3);

    @Column(nullable = false)
    private Boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
