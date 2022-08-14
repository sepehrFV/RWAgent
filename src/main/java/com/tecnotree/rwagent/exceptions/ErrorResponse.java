package com.tecnotree.rwagent.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyyMMdd hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;

    public ErrorResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, LocalDateTime timeStamp, String message) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.message = message;
    }


}
