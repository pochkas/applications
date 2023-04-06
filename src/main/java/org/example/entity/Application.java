package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Application implements Comparable<Application> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String username;

    public Application() {
    }

    public Application(String username, String message, MessageStatus status, LocalDateTime dateTime) {
        this.username = username;
        this.message = message;
        this.status = status;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(Application application) {
        return getDateTime().compareTo(application.getDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) && Objects.equals(message, that.message) && status == that.status && Objects.equals(dateTime, that.dateTime) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, status, dateTime, username);
    }
}
