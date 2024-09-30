package com.login.register.authentication.payloadresponse;

public class LoginMessage {

    private String message;   // Change fields to private for encapsulation
    private Boolean status;

    // Constructor with parameters
    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    // Default constructor (Optional, useful for deserialization)
    public LoginMessage() {
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // Override toString() for better logging and debugging
    @Override
    public String toString() {
        return "LoginMessage{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
