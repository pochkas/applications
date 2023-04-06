package org.example.dto;


import java.time.LocalDateTime;
import java.util.Objects;


public class ApplicationDTO {

    private String message;
    private LocalDateTime dateTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationDTO that = (ApplicationDTO) o;
        return Objects.equals(message, that.message) &&Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, dateTime);
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
