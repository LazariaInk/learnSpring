package com.pluriverse.learnSpring.model;


import jakarta.validation.constraints.Size;

public class User {
    private long id;
    @Size(min = 1, message = "Name should have more than one character")
    private String name;

    public User(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {}
}
