package com.ascodev.newshubbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_logs")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
}