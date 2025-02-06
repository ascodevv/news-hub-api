package com.ascodev.newshubbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "news_id", referencedColumnName = "id", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

}
