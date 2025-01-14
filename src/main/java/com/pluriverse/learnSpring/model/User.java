package com.pluriverse.learnSpring.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class User {
    private long id;
    @Size(min = 1, message = "Name should have more than one character")
    private String name;
    @Email
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

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

    public User() {}
}
