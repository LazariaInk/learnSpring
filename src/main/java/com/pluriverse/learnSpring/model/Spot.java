package com.pluriverse.learnSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@Entity
public class Spot {
    public Spot(long id, Date date, String coordinates, String description, User user) {
        this.id = id;
        this.date = date;
        this.coordinates = coordinates;
        this.description = description;
        this.user = user;
    }

    public Spot () {}
    @Id
    @GeneratedValue
    @Column(name = "spot_id")
    private long id;

    private Date date;
    private String coordinates;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
