package com.malsum.inventoryservice.exceptions;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;


public class ResponseWrapper {

    private String message;
    private int status;
    private String timestamp;

    public ResponseWrapper(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @PostConstruct
    private void setTimestamp(String timestamp) {
        this.timestamp = LocalDateTime.now().toString();
    }
}
