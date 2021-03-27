package com.aidassist.model;

public class UserResponse {
    String idToken;
    UserResponseError error;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public UserResponseError getError() {
        return error;
    }

    public void setError(UserResponseError error) {
        this.error = error;
    }
}
