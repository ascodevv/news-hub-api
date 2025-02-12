package com.ascodev.newshubbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Component
@Table(name = "users")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<UserStatus> status;

    public enum Role  implements GrantedAuthority {
        USER, ADMIN;

        @Override
        public String getAuthority() {
            return "ROLE_" + name();
        }

    }

    public enum UserStatus {
        ACTIVE, BANNED, DELETED

    }

}