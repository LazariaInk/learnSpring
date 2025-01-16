package com.pluriverse.learnSpring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_details")
public class User {
    private User(){}
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;
    @Size(min = 1, message = "Name should have more than one character")
    private String name;
    @Email
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Spot> spots;

    public User(long id, String name, String email, List<Spot> spots) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.spots = spots;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
