package com.vehicle.car.DTO;


public class ContactRequest {
    private String name;
    private String email;
    private String message;
    private boolean ctsEmployee;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCtsEmployee() {
        return ctsEmployee;
    }

    public void setCtsEmployee(boolean ctsEmployee) {
        this.ctsEmployee = ctsEmployee;
    }
}
