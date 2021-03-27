package com.aidassist.model;

public class UserResponseError {
    String message;

    public UserResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
