package library.service;

import library.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if(users.stream()
                .noneMatch(u -> u.getRole().equalsIgnoreCase(user.getRole())
                        && u.getName().equalsIgnoreCase(user.getName()))) {
            users.add(user);
            System.out.println("User " + user.getName() + " has been registered as " + user.getRole().toUpperCase());
        } else {
            System.out.println(user.getName() + " is already registered");
        }
    }

    public User findUserByName(String name, String role) {
        return users
                .stream()
                .filter(u -> u.getName().equalsIgnoreCase(name) && u.getRole().equalsIgnoreCase(role))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }
}
