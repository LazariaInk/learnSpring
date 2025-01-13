package com.pluriverse.learnSpring.controller;

import com.pluriverse.learnSpring.model.User;
import com.pluriverse.learnSpring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users/create")
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping("users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PutMapping("users/{id}")
    public void modifyUser(@RequestBody User modifiedUser,@PathVariable long id) {
        userService.modifyUser(modifiedUser,id);
    }
}
