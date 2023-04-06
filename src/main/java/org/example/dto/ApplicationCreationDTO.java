package org.example.dto;

import org.example.MessageStatus;
import org.example.entity.Application;

import java.time.LocalDateTime;
import java.util.Objects;

public class ApplicationCreationDTO {
    private String message;

    public ApplicationCreationDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationCreationDTO that = (ApplicationCreationDTO) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "message=" + message;
    }

    public Application toApplication(String username) {
        return new Application(username, getMessage(), MessageStatus.DRAFT, LocalDateTime.now());
    }
}
