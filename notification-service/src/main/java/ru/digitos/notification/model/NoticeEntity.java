package ru.digitos.notification.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String eMail;
    private String title;
    private String description;
    private String taskStatus;
    private LocalDateTime timestamp;

    public NoticeEntity() {}

    public NoticeEntity(Long userId, String eMail, String title, String description, String taskStatus) {
        this.userId = userId;
        this.eMail = eMail;
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
