package com.abhrestaurant.clientserver.model;

public class Login {

    private String status;

    public Login(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
