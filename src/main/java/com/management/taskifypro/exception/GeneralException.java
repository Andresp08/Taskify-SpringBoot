package com.management.taskifypro.exception;

import java.util.Date;

import lombok.Data;

@Data
public class GeneralException {
    private int status;
    private String message;
    private Date timestamp;

    public GeneralException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
