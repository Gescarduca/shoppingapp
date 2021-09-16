package com.malsum.catalogservice.exceptions;


import java.time.LocalTime;

public class ExceptionWrapper {
    private String message;
    private int status;
    private String timestamp;
    private String description;

    public ExceptionWrapper(String message, int status, String description) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalTime.now().toString();
        this.description = description;
    }

    public ExceptionWrapper() {
    }

    public ExceptionWrapper(String message, int status){
        this.message = message;
        this.status = status;
        this.timestamp = LocalTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
