package com.ejajapplication.thewholetruth;

public class responseModel {
    String message;
    String phone;
    String name;
    String id;
    String username;

    public responseModel(String message, String phone, String name, String id, String username) {
        this.message = message;
        this.phone = phone;
        this.name = name;
        this.id = id;
        this.username = username;
    }

    public responseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
