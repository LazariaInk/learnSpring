package com.pluriverse.learnSpring.service;

import com.pluriverse.learnSpring.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    private static List<User> users = new ArrayList<>();

    static {
        users.addAll(Arrays.asList(new User(0, "Peter"),
                new User(1, "Eva"),
                new User(2, "Edward"),
                new User(3, "Tolkien")));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(long id) {
        users.removeIf(user -> user.getId() == id);
    }

    public User getUser(long id) {
        return users.stream().filter(user -> user.getId()==id).findFirst().get();
    }

    public void modifyUser(User modifiedUser, long id) {
        for (User user : users) {
            if(user.getId() ==id) {
                user.setName(modifiedUser.getName());
                break;
            }
        }
    }
}
