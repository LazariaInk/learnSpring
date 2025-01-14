package com.pluriverse.learnSpring.service;

import com.pluriverse.learnSpring.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    static {
        users.addAll(Arrays.asList(new User(0l, "Peter", "lorienlored@gmail.com"),
                new User(1l, "Eva", "eva@gmail.com"),
                new User(2l, "Edward", "edward@gmail.com"),
                new User(3l, "Tolkien", "lotr@gmail.com")));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setId(Long.valueOf(users.size()));
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
